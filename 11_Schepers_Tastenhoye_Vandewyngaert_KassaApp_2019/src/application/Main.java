package application;
	
import controller.ArtikelController;
import database.ArtikelDbInMemory;
import database.LoadSaveFactory;
import database.Service;

import javafx.application.Application;
import javafx.stage.Stage;

import view.*;

import java.io.IOException;

import jxl.read.biff.BiffException;

public class Main extends Application {

	private Service service = new Service();
	private LoadSaveFactory factory = new LoadSaveFactory();

	@Override
	public void start(Stage primaryStage)  throws BiffException, IOException {
		System.out.println("De artikels worden uitgelezen via een " + service.getStrategy() + " bestand.");

		//Kijkt naar de Properties file en ziet of het uit een TXT of een XLS bestand moet lezen
		factory.maakLoadSaveStrategie(service.getStrategy());

		ArtikelDbInMemory artikels = new ArtikelDbInMemory();

		KassaTab kassaTab = new KassaTab();
		ArtikelTab artikelTab= new ArtikelTab();
		KlantTab klantTab = new KlantTab();

		artikels.addObserver(kassaTab);
		artikels.addObserver(artikelTab);
		artikels.addObserver(klantTab);
	}

	/*
	private void registerObserversInView() {
		service.getArtikelDB().registerObserver(articleOverviewPane);
		service.getOrderDBLocal().registerObserver(orderOverviewPane);
	}
	*/

	public static void main(String[] args) {
		launch(args);
	}
}
