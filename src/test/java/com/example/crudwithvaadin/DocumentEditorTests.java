

package com.example.crudwithvaadin;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.then;
import org.mockito.InjectMocks;

/**
 *
 */
public class DocumentEditorTests extends CustomerEditorTests
{
    @InjectMocks
    DocumentEditor editor;
        
    private final String testUrl = "htp://jdklafjldsjal;";
    
    @Test
    public void shouldStoreDocumentInRepoWhenEditorSaveClicked() 
    {
// TODO: is a call to CusomerEditorTests#shouldStoreCustomerInRepoWhenEditorSaveClicked() 
//              needed?        
        shouldStoreCustomerInRepoWhenEditorSaveClicked();
        

        
//        emptyCustomerWasSetToForm();


        this.editor.url.setValue(testUrl);
//        this.editor.lastName.setValue(LAST_NAME);


//        ArgumentMatcher<Customer> customerMatchesEditorFields = customerMatchesEditorFields();
        Project customer = new Project(FIRST_NAME, FIRST_NAME);

        
Document doc = new Document(testUrl, testUrl, customer);
        
        editor.listDocuments(customer);
        
        editor.editCustomer(doc);
        
        this.editor.save(customer);
//

//TODO: still this ;;;
        then(this.documentRepository).should().save(argThat(documentMatchesEditorFields()));
//        then(this.documentRepository).should().save(argThat(customerMatchesEditorFields()));
    }
    
ArgumentMatcher<Document> documentMatchesEditorFields() {
		return customer -> testUrl.equals(customer.getUrl()) ;//&& LAST_NAME.equals(customer.getLastName());
	}    
}
