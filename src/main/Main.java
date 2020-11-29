package main;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	int comscor = 0;
	int playscore = 0;

	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Rectangle rc = new Rectangle(700, 700);
		Rectangle rf = new Rectangle(650, 650);
		rf.setFill(Color.WHITE);
		rf.setTranslateX(20);
		rf.setTranslateY(20);
		rc.setFill(Color.DARKGREY);
		root.getChildren().add(rc);
		root.getChildren().add(rf);

		Label title = new Label("ROCK PAPER SCISSOR LIZARD SPOOK");
		title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 32));
		title.setTextFill(Color.BLUEVIOLET);
		title.setTranslateX(30);
		title.setTranslateY(20);
		root.getChildren().add(title);
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.05), evt -> title.setTextFill(Color.RED)),
				new KeyFrame(Duration.seconds(0.01), evt -> title.setTextFill(Color.BLUEVIOLET)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

		HBox cp = new HBox();
		Label namelbl = new Label("Computer");
		ImageView comimageview = new ImageView(new Image("file:src/main/0.png"));
		Label scorelbl = new Label(comscor + "");
		scorelbl.setFont(Font.font("calibiri", FontWeight.BOLD, FontPosture.REGULAR, 34));
		namelbl.setFont(Font.font("calibiri", FontWeight.BOLD, FontPosture.REGULAR, 34));
		cp.setSpacing(100);
		cp.getChildren().addAll(namelbl, comimageview, scorelbl);
		cp.setTranslateX(50);
		cp.setTranslateY(100);
		root.getChildren().add(cp);

		Label msg = new Label("START NOW");
		msg.setFont(Font.font("calibiri", FontWeight.BOLD, FontPosture.REGULAR, 24));
		msg.setTranslateX(250);
		msg.setTranslateY(220);
		root.getChildren().add(msg);

		Label stat = new Label("");
		stat.setFont(Font.font("calibiri", FontWeight.BOLD, FontPosture.REGULAR, 24));
		stat.setTranslateX(250);
		stat.setTranslateY(250);

		root.getChildren().add(stat);

		HBox p = new HBox();
		Label namelblP = new Label("Human   ");
		ImageView palyimageview = new ImageView(new Image("file:src/main/0.png"));
		Label scorelblP = new Label(playscore + "");
		scorelblP.setFont(Font.font("calibiri", FontWeight.BOLD, FontPosture.REGULAR, 34));
		namelblP.setFont(Font.font("calibiri", FontWeight.BOLD, FontPosture.REGULAR, 34));
		p.setSpacing(100);
		p.getChildren().addAll(namelblP, palyimageview, scorelblP);
		p.setTranslateX(50);
		p.setTranslateY(300);
		root.getChildren().add(p);

		Label select = new Label("Select an option");
		select.setFont(Font.font("calibiri", FontWeight.BOLD, FontPosture.REGULAR, 24));
		select.setTranslateX(250);
		select.setTranslateY(420);
		root.getChildren().add(select);

		HBox options = new HBox();
		for (int i = 0; i < 5; i++) {
			options.getChildren().add(new ImageView(new Image("file:src/main/" + (i + 1) + ".png")));
		}
		options.setSpacing(30);
		options.setTranslateX(50);
		options.setTranslateY(480);
		for (Object iterable_element : options.getChildren()) {
			ImageView ix = (ImageView) iterable_element;
			ix.setOnMouseClicked(e -> {
				System.out.println(ix.getImage().impl_getUrl());
				palyimageview.setImage(new Image(ix.getImage().impl_getUrl()));
				int h = Integer.parseInt(ix.getImage().impl_getUrl().substring(14, 15));
				System.out.println(h);

				Random r = new Random();
				int c = r.nextInt(6);
				comimageview.setImage(new Image("file:src/main/" + c + ".png"));
				if (c == h) {
					stat.setText("Draw");
					msg.setTextFill(Color.BLACK);
					stat.setTextFill(Color.BLACK);
				} else if (win(c, h)) {
					playscore++;
					stat.setText("You Win");
					msg.setTextFill(Color.DODGERBLUE);
					stat.setTextFill(Color.DODGERBLUE);
				}

				else {
					comscor++;
					stat.setText("You Loose");
					msg.setTextFill(Color.RED);
					stat.setTextFill(Color.RED);
				}
				scorelbl.setText(comscor + " Score");
				scorelblP.setText(playscore + " Score");
				msg.setText(msg(c, h));

			});
		}
		root.getChildren().add(options);
		Scene sc = new Scene(root);
		stage.setScene(sc);
		stage.initStyle(StageStyle.UTILITY);
		stage.show();
	}

	public boolean win(int c, int h) {
		boolean win = false;
		if (h == 1) {
			if (c == 5 || c == 3)
				win = false;
			else
				win = true;
		} else if (h == 2) {
			if (c == 4 || c == 3)
				win = false;
			else
				win = true;
		} else if (h == 3) {
			if (c == 1 || c == 5)
				win = false;
			else
				win = true;
		} else if (h == 4) {
			if (c == 1 || c == 3)
				win = false;
			else
				win = true;
		} else if (h == 5) {
			if (c == 2 || c == 4)
				win = false;
			else
				win = true;
		}
		return win;
	}

	public String msg(int c, int h) {
		String msg = "";
		if (h == 1) {
			switch (c) {
			case 1:
				msg = "Rock and Rock";
				break;
			case 2:
				msg = "Paper Cover Rock";
				break;
			case 3:
				msg = "Rock Crushes Scissor";
				break;
			case 4:
				msg = "Rock crushes Lizard";
				break;
			case 5:
				msg = "Spook vaporize Rock";
				break;
			default:
				break;
			}
		} else if (h == 2) {
			switch (c) {
			case 1:
				msg = "Paper cover Rock";
				break;
			case 2:
				msg = "Paper and Paper";
				break;
			case 3:
				msg = "Scissor cut Paper";
				break;
			case 4:
				msg = "Lizard eat Paper";
				break;
			case 5:
				msg = "Paper disproves Spook";
				break;
			default:
				break;
			}
		} else if (h == 3) {
			switch (c) {
			case 1:
				msg = "Rock crushes Scissor";
				break;
			case 2:
				msg = "Scissor cut Paper";
				break;
			case 3:
				msg = "Scissor and Scissor";
				break;
			case 4:
				msg = "Scissor cut Lizard";
				break;
			case 5:
				msg = "Scook smash Scissor";
				break;
			default:
				break;
			}
		} else if (h == 4) {
			switch (c) {
			case 1:
				msg = "Rock crushes Lizard";
				break;
			case 2:
				msg = "Lizard eat Paper";
				break;
			case 3:
				msg = "Scissor cut Lizard";
				break;
			case 4:
				msg = "Lizard and Lizard";
				break;
			case 5:
				msg = "Lizard poison Spook";
				break;
			default:
				break;
			}
		} else if (h == 5) {
			switch (c) {
			case 1:
				msg = "Spook vaporize Rock";
				break;
			case 2:
				msg = "Paper disproves Spook";
				break;
			case 3:
				msg = "Scook smash Scissor";
				break;
			case 4:
				msg = "Lizard poison Spook";
				break;
			case 5:
				msg = "Spook and Spook";
				break;
			default:
				break;
			}
		}
		return msg;
	}
}
