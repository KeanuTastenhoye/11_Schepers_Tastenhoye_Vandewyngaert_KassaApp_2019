package view;

import controller.ArtikelController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import jxl.read.biff.BiffException;

import java.io.IOException;

public class KlantView {
	private Stage stage = new Stage();

	public KlantView() throws BiffException, IOException {
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);
		stage.setX(775);
		stage.setY(20);

		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);

		ArtikelController artikelController = new ArtikelController();

		KlantTab klant = new KlantTab();

		BorderPane borderPane = new KlantMainPane(klant);

		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());

		root.getChildren().add(borderPane);

		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
}