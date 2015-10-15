package com.raghu.crawling

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.raghu.crawling.models.Doctor;
import com.raghu.crawling.repositories.DoctorRepository
import com.raghu.crawling.service.PractoService;

@SpringBootApplication
class WebCrawlingSampleApplication implements CommandLineRunner{

	@Autowired
	PractoService practoService;
	
	static void main(String[] args) {
        ConfigurableApplicationContext  ctx=SpringApplication.run WebCrawlingSampleApplication, args
    }

	//This will execute on start of application
	@Override
	public void run(String... args) throws Exception {
		println "Start"
		
		practoService.process();
		
		List<Doctor> doctors = practoService.getDoctors();
		if(doctors) doctors.each { println it }
		
		println "end"
	}
}
