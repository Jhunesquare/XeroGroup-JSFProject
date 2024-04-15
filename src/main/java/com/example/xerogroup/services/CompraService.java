package com.example.xerogroup.services;

import com.example.xerogroup.models.Tarifa;
import com.example.xerogroup.repositories.CompraRepository;

import java.util.List;

public class CompraService {
    CompraRepository compraRepository;
    public CompraService(){
        compraRepository = new CompraRepository();
    }
    public void comprar(Tarifa tarifa, String cliente, String vendedor) { compraRepository.comprar(tarifa, cliente, vendedor); }
}
