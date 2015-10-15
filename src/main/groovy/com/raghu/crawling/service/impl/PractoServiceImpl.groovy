package com.raghu.crawling.service.impl

import org.codehaus.groovy.transform.trait.Traits.Implemented
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements

import com.raghu.crawling.models.Doctor
import com.raghu.crawling.repositories.DoctorRepository;
import com.raghu.crawling.service.PractoService;;

@Service
class PractoServiceImpl implements PractoService{

	@Value('${practo.defaultUrl}')
	String defaultUrl
	
	//no of pages processed
	int count = 0;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Override
	public void process(String url=defaultUrl) {
		
		//Fetches the Page data
		Document doc = Jsoup.connect(url).userAgent(userAgent).timeout(10000).get();
		Elements listingRows = doc.select(".listing-row").not(".wide-featured-listing")
		
		//Collects the doctors info
		def doctors = [] 
		listingRows.each  { Element listingRow ->
			Doctor doctor=new Doctor();
			doctor.with {
				id= listingRow.select(".doc-details-block a").first().attr("data-doctor-id")
				name= listingRow.select(".doc-details-block h2").first().text()
				recommends= listingRow.select(".doc-availability-block .recommend").first().ownText()
			}
			doctors << doctor
		}
		
		//save to DB
		doctorRepository.save(doctors)

		// Process next page		
		Element element = doc.select(".page-link-container .page_link.page_link_next").first()
		if(element && count++ < 2){// count < 2 is for processing only two pages
			process(defaultUrl+element.attr("href"));
		}
				
	}

	@Override
	public List<Doctor> getDoctors() {
		return doctorRepository.findAll();
	}

}
