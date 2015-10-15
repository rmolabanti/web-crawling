package com.raghu.crawling.service

import java.util.List;

import org.springframework.stereotype.Service

import com.raghu.crawling.models.Doctor;;

interface PractoService {
	
	//String constants
	final String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21"
	
	void process();
	void process(String url);
	List<Doctor> getDoctors()
}
