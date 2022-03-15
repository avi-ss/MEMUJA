/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.e2.memuja.daw.e2;

import com.daw.e2.memuja.daw.e2.Usuarios.Usuario;
import com.daw.e2.memuja.daw.e2.Usuarios.UsuariosDAO;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;
/**
 *
 * @author Venza
 */

@ApplicationScoped
public class UsuariosIdentityStore implements IdentityStore{
    
    @Inject @DAOJpa
    private UsuariosDAO usuariosDAO;
    
    public UsuariosIdentityStore() {
    }

    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {
        //Recuperar credenciales proporcionadas por el servidor
        String username = usernamePasswordCredential.getCaller();
        String password = usernamePasswordCredential.getPasswordAsString();
        //Ejemplo simple de verificación de credenciales
        Usuario usuario = usuariosDAO.buscaPorUsuario(username);
        String validPassword = usuario.getContraseña();
        if (validPassword != null && validPassword.equals(password)) {
            //Autenticación completada, obtener los roles del usuario...
            Set<String> roles = new HashSet<>(Arrays.asList(usuario.getRol()));
            //Pasar datos del usuario al servidor
            return new CredentialValidationResult(username, roles);
        }
        return INVALID_RESULT; //Autenticación inválida
    }
}
