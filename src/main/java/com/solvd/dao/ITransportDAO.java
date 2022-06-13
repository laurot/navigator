package com.solvd.dao;

import java.util.List;

import com.solvd.bin.Transport;

public interface ITransportDAO extends IBaseDAO<Transport>{
    List<Transport> getAllTransports();
}
