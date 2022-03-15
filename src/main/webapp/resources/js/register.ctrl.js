/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global fetch */

let el = selector => document.querySelector(selector);

class RegisterCtrl {
    constructor() {
        this.srvUrl = "api/memuja"; //REST service url
    }

    init() {
        el('#registerForm').addEventListener('submit', event => {
            this.alta(event);
        });
    }

    alta(event) { //onsubmit handler

        event.preventDefault();

        let nombre = el('[name="usuario"]').value;
        let correo = el('[name="correo"]').value;
        let contraseña = el('[name="clave1"]').value;
        let contraseña2 = el('[name="clave2"]').value;
        
        if(contraseña !== contraseña2){
            el('#errores').innerHTML = "Las contraseñas no coinciden.";
            return;
        }

        let usuario = {
            correo: correo,
            usuario: nombre,
            contraseña: contraseña
        };

        this.enviaUsuario(usuario)
                .then(enviado => {
                    if (enviado) {
                        el('#registerForm').reset();
                    }
                });
    }

    enviaUsuario(usuario) { //ajax request

        let enviado = false;

        return fetch(this.srvUrl, {
            method: 'POST',
            body: JSON.stringify(usuario),
            headers: {
                'Content-type': 'application/json',
                'accept': 'application/json'
            }
        }).then(response => {
            if (response.ok) {
                enviado = true; //libro accepted in server
            } //else bean-validation errors!
            return response.json();
        }).then(response => {
            let error = "";
            if (enviado === true) {
                console.log(`Confirmado registro del usuario: ${response.correo}`);
                window.alert("Registro satisfactorio.");
            } else { //show bean-validation errors
                console.warn('ERRORES');
                response.forEach(function (valor) {
                    error += valor.message + "</br>";
                    console.warn(valor);
                });
            }
            el('#errores').innerHTML = error;
            return enviado;
        }).catch(ex => { //Network error
            console.error("Error en conexión");
            el('#errores').innerHTML = "Error en conexión";
            return enviado;
        });
    }
}

window.addEventListener('load', () => {
    //Create and initialize controller
    window.ctrl = new RegisterCtrl();
    console.log('Inicializando controlador registro');
    ctrl.init();
});
