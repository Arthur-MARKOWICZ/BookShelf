package com.BookShelf.BookShelf.model.controller;

import com.BookShelf.BookShelf.model.entity.Usuario;
import com.BookShelf.BookShelf.model.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // Exibe o formulário
    @GetMapping("/cadastro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    // Processa o cadastro
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("usuario") Usuario usuario,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cadastro";
        }

        try {
            service.salvar(usuario);
            model.addAttribute("mensagem", "Usuário cadastrado com sucesso!");
        } catch (RuntimeException e) {
            result.rejectValue("email", "error.usuario", e.getMessage());
            return "cadastro";
        }

        return "redirect:/usuarios/cadastro?sucesso";
    }
}
