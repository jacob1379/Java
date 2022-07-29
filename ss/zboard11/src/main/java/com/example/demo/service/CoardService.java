package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CoardDao;
import com.example.demo.entity.Coard;

@Service
public class CoardService {
	@Autowired
	private CoardDao dao;
	
	public Optional<Coard> read(Integer bno) {
		return dao.read(bno);
	}
}
