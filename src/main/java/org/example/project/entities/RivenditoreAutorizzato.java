package org.example.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="rivenditori_autorizzati")
public class RivenditoreAutorizzato extends Venditore{
}
