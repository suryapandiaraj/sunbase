package com.sunbase.customer.repository;

import com.sunbase.customer.entity.Customer;
import com.sunbase.customer.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /*// Custom query to find customers by city
    List<Customer> findByCity(String city);

    // Custom query using JPQL
    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Customer findByEmail(@Param("email") String email);

    // Custom query using native SQL
    @Query(value = "SELECT * FROM customers WHERE phone = :phone", nativeQuery = true)
    Customer findByPhone(@Param("phone") String phone);*/

    Page<Customer> findByUser(User user, Pageable pageable);
}
