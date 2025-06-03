
package com.example.crudwithvaadin;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface DocumentRepository  extends JpaRepository<Document, Long> 
{
    List<Document> findAllByProject(Project customer);

    public void save(Project argThat);
}
