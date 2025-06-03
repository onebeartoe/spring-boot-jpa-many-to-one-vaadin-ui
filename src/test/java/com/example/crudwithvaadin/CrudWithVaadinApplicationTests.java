package com.example.crudwithvaadin;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.*;
import org.mockito.Mock;

@SpringBootTest(classes = CrudWithVaadinApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CrudWithVaadinApplicationTests {

	@Autowired
	private ProjectRepository repository;

	@Autowired
        DocumentRepository documentRepository;        
//        @Mock 
//        DocumentRepository documentRepository;
        
	@Test
	public void shouldFillOutComponentsWithDataWhenTheApplicationIsStarted() {
		then(this.repository.count()).isEqualTo(5);
	}

    @Test
    public void shouldFindTwoBauerCustomers() 
    {
        then(this.repository.findByNotesContainsIgnoreCase("Bauer")).hasSize(2);
    }
}
