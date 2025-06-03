
package com.example.crudwithvaadin;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
@SpringComponent
@UIScope
public class DocumentEditor extends VerticalLayout 
                                                    implements KeyNotifier
        
{
    // the current customer set by listDocuments()
    private Project customer;
//    private long customerId;
    
    /**
     * The currently edited customer
     */
    private Document document;    
    
    DocumentRepository docRepo;
    
    CustomerRepository customerRepo;
            
    final Grid<Document> grid;

    final TextField filterField;

    private final Button addNewBtn;
    
    VerticalLayout editor;
    
    TextField url = new TextField("URL");
    
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    
    Button cancel = new Button("Cancel");
    
    Binder binder = new Binder<>(Document.class);
    
    private ChangeHandler changeHandler;
    
//    UI ui;
        
    @Autowired
    public DocumentEditor(CustomerRepository customerRepo, DocumentRepository repo)
    {
        this.customerRepo = customerRepo;
        
        this.docRepo = repo;
                
        this.filterField = new TextField();                
        this.addNewBtn = new Button("New Document", VaadinIcon.PLUS.create());                
        HorizontalLayout actions = new HorizontalLayout(filterField, addNewBtn);
        
        this.grid = new Grid<>(Document.class);
        
        this.editor = new VerticalLayout();    
        
        editor.add(url, save);
        
        add(actions, grid, editor);
        
        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editCustomer(new Document("", "", null)));
//        addNewBtn.addClickListener(e -> editCustomer(new Document("", "", "")));
        


	// bind using naming convention
        binder.bindInstanceFields(this);                               
       
        setSpacing(true);
        
//!!TODO!!  - enable next 2 commented lines        
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        addKeyPressListener(Key.ENTER, e -> save(customer));

//!!TODO!!  - enable next 3 commented lines                
        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save(customer));        
//        delete.addClickListener(e -> delete());
//        cancel.addClickListener(e -> editCustomer(customer));



// This was false
//        setVisible(false);                
//        setVisible(false);        
        
//        ui = UI.getCurrent();
    }
    
//    @Override
//    public void onAttach() 
//    {
//        UI ui = UI.getCurrent();
////        ui = getUI();
//        
//    }

    void save(Project customer) 
    {
//        UI ui = UI.getCurrent();        
//        ui.access(() -> 
//        {
//            Customer customer = customerRepo.findById(Long.valueOf(customerId) ).get();
//            Customer customer = new Customer();
//            customer.setId(customerId);
        
            document.setCustomer(customer);                
            docRepo.save(document);        
//            changeHandler.onChange();          

            listDocuments(customer);
            
            
System.out.println("DocuentEditor#save() - end");
//        });
    }    
    
    void listDocuments(Project customer) 
    {	
        this.customer = customer;
        
        grid.setItems(docRepo.findAllByCustomer(customer) );
        
    }
    
//TODO: rename to editDocument!    
public final void editCustomer(Document c) 
        {
System.out.println("edit document: " + c);
		if (c == null) {
			setVisible(false);
			return;
		}
	
                final boolean persisted = c.getId() != null;
		
                if (persisted) {
			// Find fresh entity for editing
			// In a more complex app, you might want to load
			// the entity/DTO with lazy loaded relations for editing
			document = docRepo.findById(c.getId()).get();
                        
//                        documentEditor.listDocuments(customer);
		}
		else {
			document = c;
		}
		
                cancel.setVisible(persisted);                                                         
                
		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(document);
         
                
                // this field was showing ',' as part of the number
                var idValue = document.getId() == null ? "" : document.getId().toString();
//                id.setValue( idValue );
      
		setVisible(true);                

		// Focus first name initially
		url.focus();
	}    

    public void setChangeHandler(ChangeHandler h) 
    {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }
    
    public interface ChangeHandler {
            void onChange();
    }    
}
