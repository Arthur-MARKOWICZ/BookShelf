package com.BookShelf.BookShelf.model.service;

import com.BookShelf.BookShelf.model.entity.Usuario;
import com.BookShelf.BookShelf.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> user = usuarioRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado com email: " + username);
        }
        return user.get();
    }
}
