package com.hwua.mall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwua.commom.po.Product;
import com.hwua.mall.service.CategoryService;
import com.hwua.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;


    @RequestMapping("/product-category")
    public String product_category(Map map){
        List<Map<String, Object>> list = categoryService.queryc1();
        map.put("list", list);
        System.out.println("list = " + list);
        return "product-brand";
    }

    @RequestMapping("/product-list")
    public String product_list(Map map,String search){
        List<Product> list = null;
        search = search == ""?null:search;
        if(search != null ){
             list =  productService.querySearch(search);
        }else{
            list = productService.queryAll();
        }
        map.put("list",list);
        List<Map> list1 = categoryService.query();
        map.put("list1", JSON.toJSONString(list1));
        return "product-list";
    }

    @RequestMapping("/product-category-add")
    public String product_category_add(String treeId){
        System.out.println("treeId = " + treeId);
        return "product-category-add";
    }

    @RequestMapping("/product-add")
    public String product_add(Integer id,Map map ){
       if(id != null){
           System.out.println("id ----------= " + id);
           Product product =  productService.queryById(id);
           map.put("product",product);


       }
        List<Map> list1 = categoryService.query();
        map.put("list1", JSON.toJSONString(list1));

        return "product-add";
    }


    @RequestMapping("category_add")
    public String category_add(){


        return "product-category";
    }

    @RequestMapping("/product-c2")
    public String product_c2(Map map,String  id){
        System.out.println("id = " + id);
        List<Map<String, Object>> list = categoryService.queryc2(id);
        map.put("list", list);
        map.put("c1", id);
        System.out.println("list = " + list);
        return "product-c2";
    }


    @RequestMapping("/product-category-addc1")
    public String product_category_addc1(String name){
        int i = categoryService.add(name);
        return "redirect:/product/product-category";
    }

    @RequestMapping("/product-category-addc2")
    public String product_category_addc2(Integer c1,String name){
        int i = categoryService.addc2(c1,name);
        return "redirect:/product/product-category";
    }

    @RequestMapping("/product-addSingle")
    public String product_addSingle(Product product,HttpSession session){
        Object img = session.getAttribute("img");
        System.out.println("img ``````````= " + img);
        session.removeAttribute("img");
        product.setImgs(img.toString());
        System.out.println("````````````````product = " + product);
        productService.doInsertProduct(product);

        return "redirect:/product/product-list";
    }


    @ResponseBody
    @RequestMapping(value = "/fileupload",produces = "application/json;charset=UTF-8")
    public String filuload(MultipartFile file,  HttpServletRequest request) throws IOException {

        InputStream is = file.getInputStream();
        String filename = file.getOriginalFilename();
        String path = request.getServletContext().getRealPath("/static/images");
        String substring = path.substring(41);
        String img = substring + File.separator + filename;
        File saveFile = new File(path + File.separator + filename);
        File parentFile = saveFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        OutputStream os = new FileOutputStream(saveFile);
        int len = -1;
        byte[] bytes = new byte[1024];
        while ((len = is.read(bytes)) != -1) {
            os.write(bytes, 0, len);
        }
        os.close();
        is.close();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",filename);
        System.out.println("filename =```````````````` " + filename);

        HttpSession session = request.getSession();
        session.setAttribute("img",filename);
        return jsonObject.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/product_stop",produces = "application/json;charset=UTF-8")
    public String product_stop(Integer id){
        System.out.println("id = ``````````" + id);
        int  i = productService.doUpdateStop(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",i>0);
        return jsonObject.toJSONString();
    }

    @RequestMapping("/product-del")
    public String product_del(Integer id){

        System.out.println("id`````````````` = " + id);
        int i = productService.product_del(id);
        return "redirect:/product/product-list";
    }

}
