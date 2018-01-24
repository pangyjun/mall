package com.hwua.front.service.impl;


import com.hwua.commom.dao.AddressMapper;
import com.hwua.commom.po.Address;
import com.hwua.front.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public int doInsert(Address address1) {
        return addressMapper.doInsert(address1);
    }

    @Override
    public int delete(Integer id, Integer mid) {
        return addressMapper.doDelete(id,mid);
    }

    @Override
    public int doupdate(Address address) {
        return addressMapper.doupdate(address);
    }

    @Override
    public int updateFlag(Integer id) {
        return addressMapper.updateFlag(id);
    }
}
