package rs.raf.demo;


import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import rs.raf.demo.repositories.*;
import rs.raf.demo.services.*;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;


@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication() {
        // Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {

                this.bind(MySQLKomentarRepository.class).to(IKomentarRepository.class).in(Singleton.class);
                this.bind(MySQLVestRepository.class).to(IVestRepository.class).in(Singleton.class);
                this.bind(MySQLKategorijaRepository.class).to(IKategorijaRepository.class).in(Singleton.class);
                this.bind(MySQLTagRepository.class).to(ITagRepository.class).in(Singleton.class);
                this.bind(MySQLKorisnikRepository.class).to(IKorisnikRepository.class).in(Singleton.class);

                this.bindAsContract(KomentarService.class);
                this.bindAsContract(VestService.class);
                this.bindAsContract(KategorijaService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(KorisniciService.class);

//                this.bind(InMemorySubjectRepository.class).to(SubjectRepository.class).in(Singleton.class);
//
//                this.bindAsContract(SubjectService.class);
            }
        };
        register(binder);

        // Ucitavamo resurse
        packages("rs.raf.demo.resources");
    }
}