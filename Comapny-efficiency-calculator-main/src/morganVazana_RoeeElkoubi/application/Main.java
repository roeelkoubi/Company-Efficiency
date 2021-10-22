package morganVazana_RoeeElkoubi.application;

import javafx.application.Application;
import javafx.stage.Stage;
import morganVazana_RoeeElkoubi.controller.SystemController;
import morganVazana_RoeeElkoubi.model.Company;
import morganVazana_RoeeElkoubi.model.ICompany;
import morganVazana_RoeeElkoubi.view.AbstractView;
import morganVazana_RoeeElkoubi.view.View;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		ICompany theModel = new Company();
		AbstractView theView = new View(primaryStage);
		SystemController controller = new SystemController(theModel, theView);
//		theModel.addHardCodedObjects();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
