package com.example.crudwithvaadin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	List<Project> findByLastNameStartsWithIgnoreCase(String lastName);
}
