package be.vdab.oefartikelsnartikelgroepen.repositories;


import be.vdab.oefartikelsnartikelgroepen.domain.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    //wat zijn de artikels met prijs tss min en max?
    List<Artikel> findByPrijsBetween(BigDecimal van, BigDecimal tot);
    //wat is hoogste artikelprijs?
    @Query("select max(a.prijs) from Artikel a")
    BigDecimal findHoogstePrijs();
    //wat zijn artikels met artikelgroep en bepaalde naam?
    List<Artikel> findByArtikelgroepNaam(String naam);
}
