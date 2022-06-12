package com.solvd.dao;

import java.util.List;
import java.util.Set;

import com.solvd.bin.Transport;

public interface ITransportDAO extends IBaseDAO<Transport>{
    Set<Transport> getAllTransports();
}
