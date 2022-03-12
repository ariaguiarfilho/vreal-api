package com.vreal.api.resource.seguranca;

import com.vreal.domain.service.seguranca.PermissaoPerfilService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/permissao-perfil")
public class PermissaoPerfilResource {


    private PermissaoPerfilService permissaoPerfilService;



    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void carregarPermissaoPerfil() {
        permissaoPerfilService.carregarPermissoesDoPerfil();

    }
}
