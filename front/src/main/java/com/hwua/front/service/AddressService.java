package com.hwua.front.service;


import com.hwua.commom.po.Address;

public interface AddressService {
    int doInsert(Address address1);

    int delete(Integer id, Integer mid);

    int doupdate(Address address);

    int updateFlag(Integer id);
}
