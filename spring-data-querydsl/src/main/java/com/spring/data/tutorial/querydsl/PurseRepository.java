package com.spring.data.tutorial.querydsl;

import com.querydsl.core.types.Predicate;
import com.spring.data.tutorial.querydsl.Purse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface PurseRepository extends JpaRepository<Purse,String>,QueryDslPredicateExecutor<Purse> {

    List<Purse> findAll(Predicate predicate);
}
