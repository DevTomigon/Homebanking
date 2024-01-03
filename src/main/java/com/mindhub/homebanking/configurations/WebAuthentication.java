    package com.mindhub.homebanking.configurations;
    import com.mindhub.homebanking.Models.Client;
    import com.mindhub.homebanking.Repositories.ClientRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;

    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
    import org.springframework.security.core.authority.AuthorityUtils;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.security.crypto.factory.PasswordEncoderFactories;
    import org.springframework.security.crypto.password.PasswordEncoder;

    @Configuration

    public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

        @Autowired
        private ClientRepository clientRepository;

        @Override
        public void init(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

            authenticationManagerBuilder.userDetailsService (inputName ->{
                Client client = clientRepository.findByEmailIgnoreCase (inputName);

                if(client != null){
                    return new User (client.getEmail (), client.getPassword (),
                            AuthorityUtils.createAuthorityList ( client.getRole ().toString () ) );
                }else{
                    throw new UsernameNotFoundException ("No te encontre amigo");
                }
            });
        }
        @Bean
        public PasswordEncoder passwordEncoder(){
            return PasswordEncoderFactories.createDelegatingPasswordEncoder ();
        }
    }
