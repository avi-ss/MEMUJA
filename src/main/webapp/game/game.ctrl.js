/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global fetch */

let el = selector => document.querySelector(selector);

class GameCtrl {
    constructor() {
        this.srvUrl = "../api/game"; //REST service url
        this.memes = [];
        this.meme;
    }

    init() {
        this.cargaMemes();
    }

    cargaMemes() {
        return fetch(this.srvUrl)
                .then(response => response.json())
                .then(memes => {
                    this.memes = memes;
                    console.log(this.memes);
                    return true;
                });
    }

    mostrarMeme(id) {
        let recibido = false;

        return fetch(this.srvUrl + "/" + id, {
            method: 'GET',
            headers: {
                'accept': 'application/json'
            }
        }).then(response => {
            if (response.ok) {
                recibido = true;
            }
            return response.json();
        }).then(response => {
            if (recibido === true) {
                console.log('Meme recibido: ' + response);
                el('#titulo').innerHTML = `${response.titulo}`;
                el('#usuario').innerHTML = `${response.usuario.usuario}`;
                
                var img = el("#imagen");
                img.src = "/MEMUJA-DAW-E2/images/" + `${response.rutaImg}`;
                
            } else { //show bean-validation errors
                console.log('Error al encontrar el meme');
            }
        }).catch(ex => { //Network error
            console.error("Error en conexión");
        });
    }
}