package org.cryptomator.ui.mainwindow;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.cryptomator.ui.addvaultwizard.AddVaultWizardComponent;
import org.cryptomator.ui.common.FXMLLoaderFactory;
import org.cryptomator.ui.common.FxController;
import org.cryptomator.ui.common.FxControllerKey;
import org.cryptomator.ui.common.FxmlFile;
import org.cryptomator.ui.common.FxmlScene;

import javax.inject.Provider;
import java.util.Map;
import java.util.ResourceBundle;

@Module(subcomponents = {AddVaultWizardComponent.class})
abstract class MainWindowModule {

	@Provides
	@MainWindow
	@MainWindowScoped
	static FXMLLoaderFactory provideFxmlLoaderFactory(Map<Class<? extends FxController>, Provider<FxController>> factories, ResourceBundle resourceBundle) {
		return new FXMLLoaderFactory(factories, resourceBundle);
	}

	@Provides
	@MainWindow
	@MainWindowScoped
	static Stage provideStage() {
		Stage stage = new Stage();
		// TODO: min/max values chosen arbitrarily. We might wanna take a look at the user's resolution...
		stage.setMinWidth(650);
		stage.setMinHeight(440);
		stage.setMaxWidth(1000);
		stage.setMaxHeight(700);
		stage.initStyle(StageStyle.UNDECORATED);
		return stage;
	}

	@Provides
	@FxmlScene(FxmlFile.MAIN_WINDOW)
	@MainWindowScoped
	static Scene provideMainScene(@MainWindow FXMLLoaderFactory fxmlLoaders) {
		return fxmlLoaders.createScene("/fxml/main_window.fxml");
	}

	// ------------------

	@Binds
	@IntoMap
	@FxControllerKey(MainWindowController.class)
	abstract FxController bindMainWindowController(MainWindowController controller);

	@Binds
	@IntoMap
	@FxControllerKey(VaultListController.class)
	abstract FxController bindVaultListController(VaultListController controller);

	@Binds
	@IntoMap
	@FxControllerKey(VaultDetailController.class)
	abstract FxController bindVaultDetailController(VaultDetailController controller);

	@Binds
	@IntoMap
	@FxControllerKey(VaultListCellController.class)
	abstract FxController bindVaultListCellController(VaultListCellController controller);

}
