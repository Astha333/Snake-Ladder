package com.example.snakeandladder;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DiceRoleSnake extends Application {

    public int rand;
    public Label randResult;

    public int cirPos[][] = new int[10][10];
    public int ladderPosition[][] = new int[6][3];

    public static final int Tile_Size = 70;
    public static final int width = 10;
    public static final int height = 10;

    public Circle player1;
    public Circle player2;

    public int playerPosition1 = 1;
    public int playerPosition2 = 1;

    public boolean player1Turn = true;
    public boolean player2Turn = true;

    public static int player1XPos =35;
    public static int player1YPos = 665;


    public static int player2XPos = 35;
    public static int player2YPos = 665;

    public int posCir1 = 1;
    public int posCir2 = 1;

    public boolean gameStart = false;
    public Button gameButton;

    private Group tileGroup = new Group();

    private Parent createConetent(){
        Pane root = new Pane();
        root.setPrefSize(width*Tile_Size, (height * Tile_Size)+80);
        root.getChildren().addAll(tileGroup);

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                Tile tile = new Tile(Tile_Size,Tile_Size);
                tile.setTranslateX(j * Tile_Size);
                tile.setTranslateY(i * Tile_Size);
                tileGroup.getChildren().add(tile);

                cirPos[i][j] = j*(Tile_Size - 35);
            }
        }



        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                System.out.print(cirPos[i][j]+ " ");
            }
            System.out.println();
        }

        player1 = new Circle(30);
        player1.setId("player1");
        player1.setFill(Color.AQUA);
        player1.getStyleClass().add("sample/style.css");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);

        player2 = new Circle(30);
        player2.setId("player2");
        player2.setFill(Color.RED);
        player2.getStyleClass().add("sample/style.css");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);

        Button button2Player = new Button("Player2");
        button2Player.setTranslateX(570);
        button2Player.setTranslateY(710);
        button2Player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(player1Turn){
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        move2Player();
                        translatePlayer(player2XPos,player2YPos,player2);
                        player2Turn = false;
                        player1Turn = true;
                        playerPosition2+= rand;

                        if(player1XPos == 175 && player1YPos == 665){
                            translatePlayer(player1XPos = 105, player1YPos = 455, player1);
                        }
                    }
                }
            }
        });


        Button button1Player = new Button("Player1");
        button1Player.setTranslateX(80);
        button1Player.setTranslateY(710);
        button1Player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(player2Turn){
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        move1Player();
                        translatePlayer(player1XPos,player1YPos,player1);
                        player1Turn = false;
                        player2Turn = true;
                        playerPosition1+=rand;

                        if(player1XPos == 175 && player1YPos == 665){
                            translatePlayer(player1XPos = 105, player1YPos = 455, player1);
                        }

                    }
                }
            }
        });

        gameButton = new Button("Start Game");
        gameButton.setTranslateX(320);
        gameButton.setTranslateY(710);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameButton.setText("Game Started");
                player1XPos = 35;
                player1YPos = 665;

                player2XPos = 35;
                player2YPos = 665;

                player1.setTranslateX(player1XPos);
                player1.setTranslateY(player1YPos);
                player2.setTranslateX(player2XPos);
                player2.setTranslateY(player2YPos);
                gameStart = true;
            }
        });


        randResult = new Label("0");
        randResult.setTranslateX(300);
        randResult.setTranslateY(710);

        Image img = new Image("C:\\Users\\FX504\\IdeaProjects\\SnakeAndLadder\\src\\main\\java\\com\\example\\snakeandladder\\board2.png");
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);
        bgImage.setFitHeight(700);
        bgImage.setFitWidth(700);



        tileGroup.getChildren().addAll(bgImage,player1,player2,button2Player,button1Player,gameButton,randResult);
        return root;
    }


    private void getDiceValue(){
        rand = (int) (Math.random()*6+1);
    }

    private void move1Player(){
        for(int i = 0; i < rand; i++){
            if(posCir1 % 2 == 1){
                player1XPos+=70;
            }
            if(posCir1 % 2 == 0){
                player1XPos-=70;
            }

            if(player1XPos > 665){
                player1YPos-=70;
                player1XPos-=70;
                posCir1++;
            }

            if(player1XPos < 35){
                player1YPos-=70;
                player1XPos+=70;
                posCir1++;
            }

            if(player1XPos < 30 || player1YPos < 30){
                player1XPos = 35;
                player1YPos = 35;
                gameStart = false;
                randResult.setText("Player One Won");
                gameButton.setText("Start Again");
            }

        }
    }


    private void move2Player(){
        for(int i = 0; i < rand; i++){
            if(posCir2 % 2 == 1){
                player2XPos+=70;
            }
            if(posCir2 % 2 == 0){
                player2XPos-=70;
            }

            if(player2XPos > 665){
                player2YPos-=70;
                player2XPos-=70;
                posCir2++;
            }

            if(player2XPos < 35){
                player2YPos-=70;
                player2XPos+=70;
                posCir2++;
            }

            if(player2XPos < 30 || player2YPos < 30){
                player2XPos = 35;
                player2YPos = 35;
                gameStart = false;
                randResult.setText("Player Two Won");
                gameButton.setText("Start Again");
            }

        }
    }
    private void translatePlayer(int x, int y, Circle b) {
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),b);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createConetent());
        scene.getStylesheets().add("sample/style.css");
        primaryStage.setTitle("Snake and Ladder");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}