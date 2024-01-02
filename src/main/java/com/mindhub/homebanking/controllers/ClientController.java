    package com.mindhub.homebanking.controllers;
    import com.mindhub.homebanking.Models.Client;
    import com.mindhub.homebanking.Repositories.ClientRepository;

    import com.mindhub.homebanking.dto.ClientDTO;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/clients")
    public class ClientController {

        @Autowired
        private ClientRepository clientRepository;

        @Autowired
        public PasswordEncoder passwordEncoder;

        @PostMapping("")
        public ResponseEntity<String> createClient
                (@RequestParam String firstName ,
                 @RequestParam String lastName ,
                 @RequestParam String email ,
                 @RequestParam String password) {

            if (firstName.isBlank ()) {
                return new ResponseEntity<> ( "El nombre no puede estar vacio " , HttpStatus.FORBIDDEN );
            }
            if (lastName.isBlank ()) {
                return new ResponseEntity<> ( "El apellido no puede estar vacio " , HttpStatus.FORBIDDEN );
            }
            if (email.isBlank ()) {
                return new ResponseEntity<> ( "El email no puede estar vacio " , HttpStatus.FORBIDDEN );
            }
            if (password.isBlank ()) {
                return new ResponseEntity<> ( "La contraseña no puede estar vacio " , HttpStatus.FORBIDDEN );
            }

            if (clientRepository.findByEmailIgnoreCase ( email ) != null) {
                return new ResponseEntity<> ( "Email ya esta en uso" , HttpStatus.FORBIDDEN );
            }

            Client client = new Client ( firstName , lastName , email , passwordEncoder.encode ( password ) );

            clientRepository.save ( client );
            return new ResponseEntity<> ( "registrado con exito pa" , HttpStatus.CREATED );
        }

        @GetMapping("/current")
        public ResponseEntity<Object> getOneClient(Authentication authentication) {

            Client client = clientRepository.findByEmailIgnoreCase(authentication.getName());

            ClientDTO clientDTO = new ClientDTO ( client );
            return new ResponseEntity<> ( clientDTO , HttpStatus.OK );
        }
    }


