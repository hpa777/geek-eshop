package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o " +
            "from Order o " +
            "inner join o.user u " +
            "where u.username = :username")
    List<Order> findAllByUsername(String username);
}
