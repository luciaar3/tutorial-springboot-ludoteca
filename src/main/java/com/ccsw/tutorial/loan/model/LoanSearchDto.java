package com.ccsw.tutorial.loan.model;

import org.springframework.data.domain.Pageable;

import java.util.Date;

public class LoanSearchDto {

    private Pageable pageable;
    private Long idGame;
    private Long idClient;
    private Date date;

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
