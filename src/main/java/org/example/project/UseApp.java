package org.example.project;

import org.example.project.dao.VenditoreDao;
import org.example.project.entities.DistributoreAutomatico;
import org.example.project.enums.StatoDistributore;

public class UseApp {
    public static void main(String[] args) {

        VenditoreDao venditoreDao = new VenditoreDao();

        DistributoreAutomatico distributoreAutomatico = new DistributoreAutomatico();
        distributoreAutomatico.setStato(StatoDistributore.ATTIVO);

    }
}
