package com.example.crudwithvaadin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudWithVaadinApplication 
{
	private static final Logger log = LoggerFactory.getLogger(CrudWithVaadinApplication.class);

	public static void main(String[] args) 
        {
		SpringApplication.run(CrudWithVaadinApplication.class);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repository, DocumentRepository docRepo) 
        {
		return (args) -> {
			// save a couple of customers
                        
                        var c1 = new Project("Jack", "Bauer");
                        
                        var c5 = new Project("Michelle", "Dessler");
                        
			repository.save(c1);
			repository.save(new Project("Chloe", "O'Brian"));
			repository.save(new Project("Kim", "Bauer"));
			repository.save(new Project("David", "Palmer"));
			repository.save(c5);

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Project customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
//                        var targetId = 1L;
                        var targetId = 5976359L;
			Project customer = repository.findById(targetId).get();
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
			log.info("--------------------------------------------");
			for (Project bauer : repository
					.findByLastNameStartsWithIgnoreCase("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
                        
                        // save a few docs
                        var doc1a = new Document("http://server/doc1.pdf", "some notes", c1);
                        docRepo.save(doc1a);
                        
                        var doc1b = new Document("http://server/doc1.pdf", "some notes", c1);
                        docRepo.save(doc1b);
                        
                        var doc5a = new Document("http://server/doc1.pdf", "dafdsome notes", c5);
                        docRepo.save(doc5a);
                        
                        // look for the saved doc1
                        var testDocs = docRepo.findAllByCustomer(c1);
                        log.info("Retrieved Document:");
                        log.info(testDocs.toString() );
		};
	}

}
