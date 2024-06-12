package com.sunbase.customer.repository;

import com.sunbase.customer.entity.Customer;
import com.sunbase.customer.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findByUser(User user, Pageable pageable);
}
