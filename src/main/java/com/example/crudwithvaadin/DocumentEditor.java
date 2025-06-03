
package com.example.crudwithvaadin;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 *
 */
@SpringComponent
@UIScope
public class DocumentEditor extends VerticalLayout implements KeyNotifier
{
    DocumentRepository docRepo;
    
    final Grid<Document> grid;

    final TextField filterField;

    private final Button addNewBtn;
    
    VerticalLayout editor;
        
    @Autowired
    public DocumentEditor(DocumentRepository repo)
    {
        this.docRepo = repo;
        this.editor = new VerticalLayout();
        this.grid = new Grid<>(Document.class);
        this.filterField = new TextField();
        this.addNewBtn = new Button("New Document", VaadinIcon.PLUS.create());

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filterField, addNewBtn);

        add(actions, grid, editor);
    }

    void listDocuments(Customer customer) 
    {	
        
        grid.setItems(docRepo.findAllByCustomer(customer) );
        
    }
}
