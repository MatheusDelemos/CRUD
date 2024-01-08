package com.matheusdelemos.crud.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Class responsavel por receber a requisição e buscar nas classes Os nomes referentes abaixo
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
 //procura todos os "Name" e assim em diante
    List<UserModel> findByName(String name);

    Optional<UserModel> findById(UUID id);

   Optional<UserModel> findByEmail(String email);

}
