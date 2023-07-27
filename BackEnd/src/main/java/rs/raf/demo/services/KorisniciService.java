package rs.raf.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.demo.entities.Kategorija;
import rs.raf.demo.entities.Korisnik;
import rs.raf.demo.repositories.IKategorijaRepository;
import rs.raf.demo.repositories.IKorisnikRepository;
import rs.raf.demo.response.LoginResponse;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class KorisniciService {

    public KorisniciService() {
        System.out.println(this);
    }

    @Inject
    private IKorisnikRepository korisnikRepository;
    
    public List<Korisnik> all() {
        return this.korisnikRepository.all();
    }
    
    public Korisnik findById(int id) {
        return this.korisnikRepository.findById(id);
    } 
    
    public Korisnik insert(Korisnik korisnik) {
        this.korisnikRepository.insert(korisnik);
        return korisnik;
    }

    public LoginResponse login(String email, String password)
    {
        String hashedPassword = DigestUtils.sha256Hex(password);

        Korisnik korisnik = this.korisnikRepository.findUser(email);
        if (korisnik == null || !korisnik.getHashedPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu
        String jwt = JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("role", korisnik.getTip())
                .sign(algorithm);

        String imePrezime = korisnik.getIme() + " " + korisnik.getPrezime();
        String role = korisnik.getTip();

        return new LoginResponse(jwt, imePrezime, role);
    }
//  ROLEEEEEEEEEEEEEEEEEEEEEEE
    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();
//        jwt.getClaim("role").asString();

        Korisnik korisnik = this.korisnikRepository.findUser(email);

        if (korisnik == null){
            return false;
        }

        return true;
    }


    public Korisnik aktivirajDeaktiviraj(Korisnik korisnik) {
        Korisnik korisnikDb = this.korisnikRepository.aktivirajDeaktiviraj(korisnik);
        return korisnikDb;
    }

    public Korisnik edit(Korisnik korisnik) {
        Korisnik korisnikDb = this.korisnikRepository.edit(korisnik);
        return korisnikDb;
    }
}
