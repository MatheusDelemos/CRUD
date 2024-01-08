package com.matheusdelemos.crud.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="tb_users")
@Table(name="users")
// usando o @DATA para criar getters e setters, @Entity definindo um nome para class entidade, E @Table para definir o nome da tabela no BD

public class UserModel {
//@Id para definir a chave unica id, generateValue para gerar um valor aleatorio e recebendo o UUID como pramtro, UUID sendo um identificar unico universal
  @Id
  @GeneratedValue(generator = "UUID")
   private UUID id;

//@Column denifine o nome das colunas no BD
  @Column(name="name")
  private String  name;

  @Column(name="email",unique = true)
  private String email;

  @Column(name="password")
  private String password;

  @Column(name="birth_date")
  private LocalDate birth_date;

  @Column(name="create_at")
  private LocalDate create_at;


}


