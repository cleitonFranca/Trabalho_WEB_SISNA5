/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unp.five.controllers;

import br.unp.five.controllers.util.Criptografia;
import br.unp.five.controllers.util.JsfUtil;
import br.unp.five.models.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cleiton França
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

     @EJB
    private br.unp.five.facade.LoginFacade ejbFacade;
    private Usuario usuario;
    private String nome;
    private String senha;
    private HttpSession sessionLogin;

    public HttpSession getSessionLogin() {
        return sessionLogin;
    }

    public void setSessionLogin(HttpSession sessionLogin) {
        this.sessionLogin = sessionLogin;
    }
    
    /**
     * Authenticacao
     * @return 
     * @throws java.security.NoSuchAlgorithmException 
     */
    public String login() throws NoSuchAlgorithmException {
        
        try {
            
        usuario = ejbFacade.find(this.getNome(), Criptografia.MD5(this.getSenha()));
            
            if(usuario.getIdUsuario() > 0) {
                
                System.out.println(usuario.getIdUsuario());
                sessionLogin = (HttpSession) FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getSession(true);
                sessionLogin.setAttribute("isLoggedIn", usuario);
                sessionLogin.setAttribute("idUser", usuario.getIdUsuario());
            }
            return "/private/periodicos/Busca";
            
        } catch (NullPointerException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, e);
            //ResourceBundle.getBundle("/Bundle").getString("LoginFaill")
            JsfUtil.addErrorMessage("Usuario nao encontrado!");
            return "formLogin";
        }
        

    }
    
    /**
     * Logout do sistema
     * @return 
     */
    public String logout() {
        sessionLogin.setAttribute("isLoggedIn", null);
        sessionLogin.setAttribute("idUser", null);
        return "/faces/formLogin";
        
    }
    
    public LoginController() {
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
    
    
    
}
