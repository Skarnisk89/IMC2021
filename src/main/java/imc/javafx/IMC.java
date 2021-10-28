package imc.javafx;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMC extends Application {
	
	//vista
	
	private Label pesoLabel;
	private TextField pesoTextField;
	private Label kiloLabel;
	private Label alturaLabel;
	private TextField alturaTextField;
	private Label cmLabel;
	private Label IMC;
	private Label numLabel;
	private Label resultadoLabel;

	private DoubleProperty peso = new SimpleDoubleProperty();
	private DoubleProperty altura= new SimpleDoubleProperty();
	private DoubleProperty resultado= new SimpleDoubleProperty();
	
	public void start(Stage primaryStage) throws Exception {
		
		pesoLabel = new Label("peso");
		kiloLabel = new Label("kg");
		alturaLabel = new Label ("altura");
		cmLabel = new Label("cm");
		IMC = new Label("IMC : ");
		numLabel = new Label();
		resultadoLabel = new Label ("-");
		
		pesoTextField = new TextField(); //caja de texto y predefinimos el tamaño.
		pesoTextField.setPrefWidth(50);
		
		alturaTextField = new TextField();
		alturaTextField.setPrefWidth(50);
		
		
		HBox cuadroPeso = new HBox();
		cuadroPeso.getChildren().addAll(pesoLabel, pesoTextField, kiloLabel);
		cuadroPeso.setSpacing(5);
		cuadroPeso.setAlignment(Pos.BASELINE_CENTER);
		
		HBox cuadroAltura = new HBox();
		cuadroAltura.getChildren().addAll(alturaLabel, alturaTextField, cmLabel);
		cuadroAltura.setSpacing(5);
		cuadroAltura.setAlignment(Pos.BASELINE_CENTER);
		
		HBox cuadroResultado = new HBox();
		cuadroResultado.getChildren().addAll(IMC, numLabel);
		cuadroResultado.setSpacing(5);
		cuadroResultado.setAlignment(Pos.BASELINE_CENTER);
		
		VBox root = new VBox();
		root.getChildren().addAll(cuadroPeso, cuadroAltura, cuadroResultado,resultadoLabel );
		root.setAlignment(Pos.BASELINE_CENTER);
		
		
		
		Scene scene = new Scene(root, 640, 480);
		primaryStage.setTitle("IMC");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Bindings.bindBidirectional(pesoTextField.textProperty(),peso, new NumberStringConverter());
		Bindings.bindBidirectional(alturaTextField.textProperty(),altura, new NumberStringConverter());
		
		DoubleBinding alturaM = altura.divide(100);

		// Altura al cuadrado
		DoubleBinding alturaCuadrado = alturaM.multiply(alturaM);

		// Operación
		DoubleBinding res = peso.divide(alturaCuadrado);
		resultado.bind(res);
		numLabel.textProperty().bind(resultado.asString("%.2f")); 
		
		numLabel.textProperty().addListener((o,ov,nv)->{  
			double n = res.doubleValue();
			
			if (n < 18.5) {
				resultadoLabel.setText("Delgaducho");
			}
			
			if (n >= 18.5 && n <25) {
				resultadoLabel.setText("Normalucho");
			}
			
			if (n >= 25 && n <30) {
				resultadoLabel.setText("Rubenesco");
			}
			
			if (n > 30) {
				resultadoLabel.setText("GRAVITY MODE 'ON' ");
			}
			
		});
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
	}

}
