package com.examencl2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examencl2.entity.Producto;

public interface ElectroRepository extends JpaRepository<Producto, Integer> {

}
