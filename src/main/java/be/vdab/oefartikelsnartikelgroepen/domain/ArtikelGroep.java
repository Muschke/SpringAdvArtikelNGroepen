package be.vdab.oefartikelsnartikelgroepen.domain;

import javax.persistence.*;

@Entity
@Table(name = "artikelgroepen")
public class ArtikelGroep {
    @Id @GeneratedValue
    private long id;
    private String naam;

    public ArtikelGroep(String naam) {
        this.naam = naam;
    }

    protected ArtikelGroep () {};

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
