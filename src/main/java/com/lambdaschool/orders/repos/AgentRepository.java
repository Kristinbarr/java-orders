package com.lambdaschool.orders.repos;

import com.lambdaschool.orders.models.Agent;
import org.springframework.data.repository.CrudRepository;

// could extend CrudRepository, PagingAndSortingRepository, JpaRepository(dnu)
// CrudRepository takes 2 Class params: model class anf type of id
public interface AgentRepository extends CrudRepository<Agent, Long> {

}
