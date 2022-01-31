package be.vdab.oefartikelsnartikelgroepen.repositories;

import be.vdab.oefartikelsnartikelgroepen.domain.Artikel;
import be.vdab.oefartikelsnartikelgroepen.domain.ArtikelGroep;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql({"/insertArtikelGroepen.sql", "/insertArtikels.sql"})
class ArtikelRepositoryTest  extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String ARTIKELS = "artikels";
    private final ArtikelRepository repository;

    public ArtikelRepositoryTest(ArtikelRepository repository) {
        this.repository = repository;
    }

    @Test
    void findByPrijsBetween() {
        var vijf = BigDecimal.valueOf(5);
        var vijftien = BigDecimal.valueOf(15);
        assertThat(repository.findByPrijsBetween(vijf, vijftien))
                .hasSize(countRowsInTableWhere(ARTIKELS,
                        "prijs between 5 and 15"))
                .extracting(Artikel::getPrijs)
                .allSatisfy(prijs -> assertThat(prijs).isBetween(vijf, vijftien));
    }

    @Test
    void findHoogstePrijs() {
        assertThat(repository.findHoogstePrijs()).isEqualByComparingTo(BigDecimal.valueOf(20));
    }

    @Test
    void findByNaamAndArtikelgroep() {
        var artikelgroep = "groep1";
        assertThat(repository.findByArtikelgroepNaam(artikelgroep))
                .hasSize(countRowsInTableWhere(ARTIKELS, "artikelGroepId = (select id from artikelGroepen where naam = 'groep1')"))
                .extracting(Artikel::getArtikelgroep)
                .extracting(ArtikelGroep::getNaam)
                .isEqualTo(artikelgroep);
    }
}