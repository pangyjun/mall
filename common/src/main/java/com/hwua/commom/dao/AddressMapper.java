package com.hwua.commom.dao;


import com.hwua.commom.po.Address;

public interface AddressMapper {
    public int doInsert(Address address1);

    int doDelete(Integer id, Integer mid);

    int doupdate(Address address);

    int updateFlag(Integer id);

}
