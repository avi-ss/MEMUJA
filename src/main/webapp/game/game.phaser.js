/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Phaser, fetch*/

// Controller Variables
var controller = new GameCtrl();
console.log('Inicializando controlador GameCtrl');
controller.init();

var config = {
    type: Phaser.AUTO,
    width: 800,
    height: 600,
    physics: {
        default: 'arcade',
        arcade: {
            gravity: {y: 300},
            debug: false
        }
    },
    scene: {
        preload: preload,
        create: create,
        update: update
    }
};

var game = new Phaser.Game(config);

//Game Objects
var platforms;

var player;
var playerVelX;
var playerAcceleration = 20;

var cursors;

var stars;

var scoreText;
var score = 0;

function preload() {
    this.load.image('sky', 'assets/sky.png');
    this.load.image('ground', 'assets/platform.png');
    this.load.image('star', 'assets/star.png');
    this.load.spritesheet('dude',
            'assets/dude.png',
            {frameWidth: 32, frameHeight: 48}
    );
}

function create() {
    this.add.image(400, 300, 'sky');

    platforms = this.physics.add.staticGroup();
    platforms.create(400, 568, 'ground').setScale(2).refreshBody();

    player = this.physics.add.sprite(100, 450, 'dude');
    player.setBounce(0.2);
    player.setCollideWorldBounds(true);
    player.body.setGravityY(300);
    playerVelX = 0;

    this.physics.add.collider(player, platforms);

    this.anims.create({
        key: 'left',
        frames: this.anims.generateFrameNumbers('dude', {start: 0, end: 3}),
        frameRate: 10,
        repeat: -1
    });

    this.anims.create({
        key: 'turn',
        frames: [{key: 'dude', frame: 4}],
        frameRate: 20,
    });

    this.anims.create({
        key: 'right',
        frames: this.anims.generateFrameNumbers('dude', {start: 5, end: 8}),
        frameRate: 10,
        repeat: -1
    });

    cursors = this.input.keyboard.createCursorKeys();

    stars = this.physics.add.group({
        key: 'star',
        repeat: controller.memes.length,
        setXY: {x: 30, y: 0, stepX: (800/controller.memes.length)}
    });

    var id = 1;
    stars.children.iterate(function (child) {
        child.setBounceY(Phaser.Math.FloatBetween(0.4, 0.8));
        child.setName(id.toString());
        id++;
    });
    
    console.log(stars.children);

    this.physics.add.collider(stars, platforms);

    this.physics.add.overlap(player, stars, collectStar, null, this);

    scoreText = this.add.text(16, -175, 'WikiPuntos: 0', {fontSize: '32px', fill: '#000'});
}

function update() {
    if (cursors.left.isDown) {
        playerVelX -= playerAcceleration;

        if (playerVelX <= -360)
            playerVelX = -360;

        player.setVelocityX(playerVelX);

        player.anims.play('left', true);
    } else if (cursors.right.isDown) {
        playerVelX += playerAcceleration;

        if (playerVelX >= 360)
            playerVelX = 360;

        player.setVelocityX(playerVelX);

        player.anims.play('right', true);
    } else {
        if (playerVelX > 0) {
            playerVelX -= playerAcceleration;
        } else if (playerVelX < 0) {
            playerVelX += playerAcceleration;
        }

        player.setVelocityX(playerVelX);

        player.anims.play('turn');
    }

    if (cursors.up.isDown && player.body.touching.down) {
        player.setVelocityY(-450);
    }
}

function collectStar(player, star) {
    star.setX(Phaser.Math.Between(12, 788));
    star.setY(0);
    star.setBounceY(Phaser.Math.FloatBetween(0.4, 0.8));
    score++;
    scoreText.setText("WikiPuntos: " + score);
    
    controller.mostrarMeme(star.name);
}
