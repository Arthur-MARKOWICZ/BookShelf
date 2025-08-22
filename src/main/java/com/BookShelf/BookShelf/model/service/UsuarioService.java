package com.BookShelf.BookShelf.model.service;

import com.BookShelf.BookShelf.model.entity.Usuario;
import com.BookShelf.BookShelf.model.enums.Role;
import com.BookShelf.BookShelf.model.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {
        String senhaHash = encoder.encode(usuario.getSenha());
        usuario.setRole(Role.USUARIO);
        usuario.setSenha(senhaHash);
        return repository.save(usuario);
    }
}
