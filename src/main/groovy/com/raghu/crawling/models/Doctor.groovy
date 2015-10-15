package com.raghu.crawling.models
import org.springframework.data.annotation.Id
import groovy.transform.ToString

@ToString(includeFields=true,includePackage=false)
class Doctor {
	@Id
	String id;
	String name;
	String recommends;
}
