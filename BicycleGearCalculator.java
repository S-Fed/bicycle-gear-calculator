import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * 
 * @author SFED
 *
 */
public class BicycleGearCalculator extends Application{
	
	NumberFormat two = new DecimalFormat("#.##");
//----Database----
	private Label lblStatus = new Label();
	private Statement stmt;  


				
//----MainMenu----	
	Label lbBasic,lbMain,lb_chainring,lb_cog,lb_tire, lbBasicExp, lbAdvancedExp, lbSuggestedExp;
	Stage mainMenu;
	Button basic,advanced,suggested,user,helpSection;
	
//----Basic+AdvancedAttributes----
	private ObservableList<Integer> chainring =
			 FXCollections.observableArrayList (44,46,48,50,51,52);
	
	private ObservableList<Integer> chainringCadence =
			 FXCollections.observableArrayList (44,46,48);
	
	private ObservableList<Integer> cog =
			FXCollections.observableArrayList (12,13,14,15,16,17,18,19);
	
	private ObservableList<Integer> intendedSpeed =
			FXCollections.observableArrayList (12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27);
	
	private ObservableList<Integer> cadence =
			FXCollections.observableArrayList (60,65,70,75,80,85,90,95,100);
	
	private ObservableList<String> tireSize =
			FXCollections.observableArrayList ("700c/29 inch","26 inch");
	
	private ComboBox<Integer> cbBasicChainring,cbAdvancedChainring,cbBasicCog,cbAdvancedCog,cbChainringCadence,cbIntendedSpeed,cbCadence;
	private ComboBox<String> cbBasicTire,cbAdvancedTire;
	
	private Button calculateBasic = new Button("Calculate");
	private Button calculateAdvanced = new Button("Calculate");
	private Button calculateCadence = new Button("Calculate");
	private Button clearBasic = new Button("Clear");
	private Button clearAdvanced = new Button("Clear");
	private Button clearCadence = new Button("Clear");
	private Button returnsBasic = new Button("Returns");
	private Button returnsAdvanced = new Button("Returns");
	private Button cadenceAdvanced = new Button("Preferred Speed by Cadence");
		
	private TextField basicResult = new TextField();
	private TextField basicSpeed = new TextField();
	private TextArea basicComment = new TextArea();
	private TextField advancedResult = new TextField();
	private TextField advancedSpeed = new TextField();
	private TextField advancedCadence = new TextField();
	private TextArea advancedComment = new TextArea();
	
//----SuggestedAttributes----
	Label lb_bikeExperience,lb_intendedBike,lb_bikeType,lb_ridingEnviroment,lb_suggestedComment;
	private Button btReturnSuggested = new Button("Return");	
	private Button calculateSuggested = new Button("Calculate");
	private Button clearSuggested = new Button("Clear");
	private TextArea suggestedComment = new TextArea();
	private ComboBox<String> cbBikeExperience,cbIntendedBike,cbBikeType,cbRidingEnvironment;
	
	private ObservableList<String> bikeExperience =
			FXCollections.observableArrayList ("Beginner","Novice","Intermediate","Advanced");
	
	private ObservableList<String> intendedBike =
				FXCollections.observableArrayList ("Leisure","Commuting","Training","Trail Riding");
	
	private ObservableList<String> ridingEnviroment =
			FXCollections.observableArrayList ("All-Purpose Trail","Flat","Some Hills","Many Hills",
					"Off-Road Trail");
	
//----HelpSectionAttributes----

	private TextArea helpSectionComment = new TextArea();
	
	
	@Override
	public void start(Stage primaryStage){ 
	
//--------------------BasicGearCalculator--------------------
			
			HBox chainring_paneBasic = new HBox(10);
			HBox cog_paneBasic = new HBox(10);
			HBox tire_paneBasic = new HBox(10);
			HBox result_paneBasic = new HBox(10);
			HBox speed_paneBasic = new HBox(10);
			HBox comment_paneBasic = new HBox(15);
			
			Label lbBasicMain = new Label("Basic Gear Calculator");
			lbBasicMain.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 36; -fx-text-fill: #FFFFFF;");
			
			lb_chainring = new Label("Chainring");
			cbBasicChainring = new ComboBox(chainring);
			cbBasicChainring.setVisibleRowCount(4);
			cbBasicChainring.setValue(44); 
			chainring_paneBasic.getChildren().addAll(lb_chainring, cbBasicChainring);
			
			lb_cog = new Label("Cog");
			cbBasicCog = new ComboBox(cog);
			cbBasicCog.setVisibleRowCount(4);
			cbBasicCog.setValue(12); 
			cog_paneBasic.getChildren().addAll(lb_cog, cbBasicCog);
			
			lb_tire = new Label("Tire Size");
			cbBasicTire = new ComboBox(tireSize);
			cbBasicTire.setVisibleRowCount(4);
			cbBasicTire.setValue("700c/29 inch"); 
			tire_paneBasic.getChildren().addAll(lb_tire, cbBasicTire);
						
			Label lb_basicResult = new Label("Gear Ratio");
			basicResult = new TextField();
			basicResult.setEditable(false);
			basicResult.setPrefColumnCount(6);
			result_paneBasic.getChildren().addAll(lb_basicResult,basicResult);
			
			Label lb_basicSpeed = new Label("Average Speed");
			basicSpeed = new TextField();
			basicSpeed.setEditable(false);
			basicSpeed.setPrefColumnCount(7);
			speed_paneBasic.getChildren().addAll(lb_basicSpeed,basicSpeed);
						
			GridPane basicCalculatorPane = new GridPane();
			basicCalculatorPane.setPrefSize(100, 100);
			basicCalculatorPane.add(lbBasicMain, 2, 0);
			basicCalculatorPane.add(returnsBasic, 0, 0);
			basicCalculatorPane.add(chainring_paneBasic, 0, 1);
			basicCalculatorPane.add(cog_paneBasic, 1, 1);
			basicCalculatorPane.add(tire_paneBasic, 2, 1);
			basicCalculatorPane.add(calculateBasic, 0, 2);
			basicCalculatorPane.add(clearBasic, 1, 2);
			basicCalculatorPane.add(result_paneBasic,3,2);
			basicCalculatorPane.add(speed_paneBasic,3, 3);
			basicCalculatorPane.setHgap(5); 
			basicCalculatorPane.setVgap(25); 
		   	basicCalculatorPane.setPadding(new Insets(5, 10, 5, 10)); 
//------------------AdvancedGearCalculator--------------------
			HBox chainring_paneAdvanced = new HBox(10);
			HBox cog_paneAdvanced = new HBox(10);
			HBox tire_paneAdvanced = new HBox(10);
			HBox result_paneAdvanced = new HBox(10);
			HBox speed_paneAdvanced = new HBox(10);
			HBox functions_paneAdvanced = new HBox(10);
			HBox comment_paneAdvanced = new HBox(15);
			
			Label lbAdvancedMain = new Label("Advanced Gear Calculator");
			lbAdvancedMain.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 36; -fx-text-fill: #9ACD32;");
			
			lb_chainring = new Label("Chainring");
			cbAdvancedChainring = new ComboBox(chainring);
			cbAdvancedChainring.setVisibleRowCount(4);
			cbAdvancedChainring.setValue(44); 
			chainring_paneAdvanced.getChildren().addAll(lb_chainring, cbAdvancedChainring);
			
			lb_cog = new Label("Cog");
			cbAdvancedCog = new ComboBox(cog);
			cbAdvancedCog.setVisibleRowCount(4);
			cbAdvancedCog.setValue(12); 
			cog_paneAdvanced.getChildren().addAll(lb_cog, cbAdvancedCog);
			
			lb_tire = new Label("Tire Size");
			cbAdvancedTire = new ComboBox(tireSize);
			cbAdvancedTire.setVisibleRowCount(4);
			cbAdvancedTire.setValue("700c/29 inch"); 
			tire_paneAdvanced.getChildren().addAll(lb_tire, cbAdvancedTire);
						
			Label lb_advancedResult = new Label("Gear Ratio");
			advancedResult = new TextField();
			advancedResult.setEditable(false);
			advancedResult.setPrefColumnCount(3);
			result_paneAdvanced.getChildren().addAll(lb_advancedResult,advancedResult);
			
			Label lb_advancedSpeed = new Label("Average Speed");
			advancedSpeed = new TextField();
			advancedSpeed.setEditable(false);
			advancedSpeed.setPrefColumnCount(7);
			result_paneAdvanced.getChildren().addAll(lb_advancedSpeed,advancedSpeed);
			
			Label lb_advancedComment = new Label("Comments");
			advancedComment = new TextArea();
			advancedComment.setEditable(false);
			advancedComment.setPrefColumnCount(20);
			advancedComment.setPrefRowCount(4);
			advancedComment.setWrapText(true);
			comment_paneAdvanced.getChildren().addAll(lb_advancedComment,advancedComment);
			
			GridPane advancedCalculatorPane = new GridPane();
			advancedCalculatorPane.add(lbAdvancedMain, 2, 0);
			advancedCalculatorPane.add(returnsAdvanced, 0, 0);
			advancedCalculatorPane.add(chainring_paneAdvanced, 0, 1);
			advancedCalculatorPane.add(cog_paneAdvanced, 1, 1);
			advancedCalculatorPane.add(tire_paneAdvanced, 2, 1);
			advancedCalculatorPane.add(calculateAdvanced, 0, 2);
			advancedCalculatorPane.add(clearAdvanced, 1, 2);
			advancedCalculatorPane.add(result_paneAdvanced,2, 3);
			advancedCalculatorPane.add(speed_paneAdvanced,2, 4);
			advancedCalculatorPane.add(cadenceAdvanced,0, 4);
			advancedCalculatorPane.add(comment_paneAdvanced,2, 4);
			
			advancedCalculatorPane.setHgap(5);
			advancedCalculatorPane.setVgap(25); 
		    advancedCalculatorPane.setPadding(new Insets(5, 10, 5, 10)); 
//--------------------------Suggested------------------------			    
			HBox suggested_bikeExperience = new HBox(10);
			HBox suggested_intendedBike = new HBox(10);
			HBox suggested_ridingEnviroment = new HBox(10);
			HBox suggested_comment = new HBox(10);
				
			Label lbSuggestedMain = new Label("Suggested Gear Pairings");
			lbSuggestedMain.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 36; -fx-text-fill: #FFF5EE;");
			
			lb_bikeExperience = new Label("Experience Level");
			cbBikeExperience = new ComboBox(bikeExperience);
			cbBikeExperience.setVisibleRowCount(4);
			cbBikeExperience.setValue("Beginner"); 
			suggested_bikeExperience.getChildren().addAll(lb_bikeExperience, cbBikeExperience);
			
			lb_intendedBike = new Label("Bike Use");
			cbIntendedBike = new ComboBox(intendedBike);
			cbIntendedBike.setVisibleRowCount(4);
			cbIntendedBike.setValue("Leisure"); 
			suggested_intendedBike.getChildren().addAll(lb_intendedBike, cbIntendedBike);
		
			lb_ridingEnviroment = new Label("Riding Environment");
			cbRidingEnvironment = new ComboBox(ridingEnviroment);
			cbRidingEnvironment.setVisibleRowCount(4);
			cbRidingEnvironment.setValue("All-Purpose Trail"); 
			suggested_ridingEnviroment.getChildren().addAll(lb_ridingEnviroment, cbRidingEnvironment);
			
			Label lb_suggestedComment = new Label("Suggested Gear Pairings");
			suggestedComment = new TextArea();
			suggestedComment.setEditable(false);
			suggestedComment.setPrefColumnCount(25);
			advancedComment.setPrefRowCount(3);
			suggestedComment.setWrapText(true);
			suggestedComment.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 14; -fx-text-fill: green;");
			suggested_comment.getChildren().addAll(lb_suggestedComment,suggestedComment);
			
			GridPane suggestedPane = new GridPane();
			suggestedPane.setHgap(5); 
			suggestedPane.setVgap(25); 
			suggestedPane.add(lbSuggestedMain, 0, 0);
			suggestedPane.add(btReturnSuggested, 2, 0);
			suggestedPane.add(suggested_bikeExperience, 0, 2);
			suggestedPane.add(suggested_intendedBike, 0, 1);
			suggestedPane.add(suggested_ridingEnviroment, 0, 3);
			suggestedPane.add(calculateSuggested, 1, 2);
			suggestedPane.add(clearSuggested,1, 3);
			suggestedPane.add(suggested_comment,0, 4);
			
 
//--------------------------mainMenu-------------------------
			HBox basic_pane = new HBox(10);
			HBox advanced_pane = new HBox(10);
			HBox suggested_pane = new HBox(10);
	
			mainMenu = primaryStage;
			lbMain = new Label("Easy Bicycle Gear Calculator");
		
				helpSection = new Button("Help Section");
		     
		        basic = new Button("Basic Gear Calculator");
		    	lbBasicExp = new Label("Use the Basic Gear Calculator");
		    	lbBasicExp.setStyle("-fx-font-size: 24;-fx-font-weight: bold;");
				basic_pane.getChildren().addAll(basic,lbBasicExp);
				
				advanced = new Button("Advanced Gear Calculator");
				lbAdvancedExp = new Label("Use the Advanced Gear Calculator with" + "\n" + "additional functions");
				lbAdvancedExp.setStyle("-fx-font-size: 24;-fx-font-weight: bold;");
				advanced_pane.getChildren().addAll(advanced,lbAdvancedExp);
				        
				suggested = new Button("SuggestedGearParings");
				lbSuggestedExp = new Label("Enter your riding information and get" + "\n" + "suggested gear pairings(Recommended for Beginners)");
				lbSuggestedExp.setStyle("-fx-font-size: 24;-fx-font-weight: bold;");
				suggested_pane.getChildren().addAll(suggested,lbSuggestedExp);
				
				
		       GridPane calculatorPane = new GridPane();
		       calculatorPane.setHgap(40); 
		       calculatorPane.setVgap(20); 
		       calculatorPane.setPadding(new Insets(5, 10, 5, 10)); 
		       calculatorPane.add(lbMain, 0, 0);                                           
		       calculatorPane.add(basic_pane, 0, 2);
		       calculatorPane.add(advanced_pane, 0, 4);
		       calculatorPane.add(suggested_pane, 0, 6);
		       calculatorPane.add(helpSection, 2, 0);
		       
		   	Scene gearCalculatorScene = new Scene(calculatorPane, 1400, 500);
		   	gearCalculatorScene.getStylesheets().add("StyleOne.css");
		   	Scene basicGearCalculatorScene = new Scene(basicCalculatorPane, 1400, 400);
		    basicGearCalculatorScene.getStylesheets().add("StyleThree.css");
			Scene advancedGearCalculatorScene = new Scene(advancedCalculatorPane, 1400, 400);
			advancedGearCalculatorScene.getStylesheets().add("StyleTwo.css");
			Scene suggestedCalculatorScene = new Scene(suggestedPane, 1000, 500);
			suggestedCalculatorScene.getStylesheets().add("StyleFour.css");
		
			
			 
			primaryStage.setTitle("Gear Calculator");
			primaryStage.setScene(gearCalculatorScene);
			primaryStage.show();		
			initializeDB();
			
		
			 
			 
			
			    //----ButtonActions----
			    basic.setOnAction(e -> primaryStage.setScene(basicGearCalculatorScene));
			    returnsBasic.setOnAction(e -> primaryStage.setScene(gearCalculatorScene));
			    advanced.setOnAction(e -> primaryStage.setScene(advancedGearCalculatorScene));
			    returnsAdvanced.setOnAction(e -> primaryStage.setScene(gearCalculatorScene));
			    suggested.setOnAction(e -> primaryStage.setScene(suggestedCalculatorScene));
			    btReturnSuggested.setOnAction(e -> primaryStage.setScene(gearCalculatorScene));
		        helpSection.setOnAction(e -> openHelpSection());
			    calculateBasic.setOnAction(e -> gearResultBasic());
			    calculateAdvanced.setOnAction(e -> gearResultAdvanced());
			    clearBasic.setOnAction(e -> clearBasic());
			    clearAdvanced.setOnAction(e -> clearAdvanced());
			    cadenceAdvanced.setOnAction(e -> AdvancedFunctions());
			    calculateCadence.setOnAction(e -> cadenceResult());
			    clearCadence.setOnAction(e-> clearCadence());
			    calculateSuggested.setOnAction(e -> gearPairings());
			    clearSuggested.setOnAction((e -> clearSuggested()));
		}
	



//--------------------------Methods--------------------------

/** Calculates the gear ratio and Average Speed
 *  @param Chainring Size
 *  @param Cog Size
 *  @return gearRatio,averageSpeed
 */ 
	public void gearResultBasic(){
		 	double result = 0;
			double speedResultTwentySix;
			double speedResultTwentyNine;
			double selections =  cbBasicChainring.getValue();
		 	double selections2 = cbBasicCog.getValue();
			String selections3 = cbBasicTire.getValue();
			result = Math.round((selections / selections2) * 10) / 10.0;
			speedResultTwentyNine = (.79 * (selections / selections2) * 8);
			speedResultTwentySix = (.69 * (selections / selections2) *8);
			String value = String.valueOf(result);
			basicResult.setText(value);
				
	if(selections3 == "700c/29 inch"){
		basicSpeed.setText(two.format(speedResultTwentyNine) + " MPH");}
	else if(selections3 == "26 inch"){
		basicSpeed.setText(two.format(speedResultTwentySix) + " MPH");}
	}
	
	
	
	/** Calculates the gear ratio and Average Speed and gear ratio comment
	 *  @param Chainring Size
	 *  @param Cog Size
	 *  @return gearRatio,averageSpeed,advancedComment
	 */ 
	public void gearResultAdvanced(){
			NumberFormat advtwo = new DecimalFormat("#.##");
			double result = 0;
			double speedResultTwentySix;
			double speedResultTwentyNine;
			double selections =  cbAdvancedChainring.getValue();
		 	double selections2 = cbAdvancedCog.getValue();
		 	String selections3 = cbAdvancedTire.getValue();
		 	
		 	result = Math.round((selections / selections2) * 10) / 10.0;
		 	speedResultTwentyNine = (.79 * (selections / selections2) * 8);
			speedResultTwentySix = (.69 * (selections / selections2) *8);
			
			String value = String.valueOf(result);
			advancedResult.setText(value);
			
			if(selections3 == "700c/29 inch"){
				advancedSpeed.setText(two.format(speedResultTwentyNine) + " MPH");}
			else if(selections3 == "26 inch"){
				advancedSpeed.setText(two.format(speedResultTwentySix) + " MPH");}
			
			
			if(result == 2.3){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 1";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");} }    
			else if(result==2.4){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 2";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==2.5){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 3";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==2.6){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 4";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==2.7){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 5";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==2.8){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 6";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==2.9){
			    String query = "SELECT * FROM gearRatio WHERE gearRatioID = 7";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==3.0){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 8";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==3.1){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 9";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==3.2){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 10";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==3.3){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 11";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==3.4){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 12";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==3.5){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 13";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==3.6){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 14";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==3.7){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 15";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==3.8){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 16";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==3.9){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 17";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==4.0){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 18";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==4.1){
				String query = "SELECT * FROM gearRatioDescription WHERE gearRatioID = 19";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==4.2){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 20";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			else if(result==4.3){
				String query = "SELECT * FROM gearRatio WHERE gearRatioID = 21";
				 try {ResultSet rs = stmt.executeQuery(query);
			  	 	if (rs.next()){advancedComment.setText( rs.getString(2));}
			  	 		else{advancedComment.setText("Record not found");}
			  		} catch (SQLException ex) {
			  			advancedComment.setText("Select Failed");}}
			}
	
	
	
	/** Calculates the gear ratio and Average Speed
	 *  @param Chainring Size
	 *  @param Cog Size
	 *  @return gearRatio,averageSpeed
	 */ 
		public void cadenceResult(){
				int result =0;
				float round =0;
				int selections =  cbChainringCadence.getValue();
			 	int selections2 = cbCadence.getValue();
				int selections3 = cbIntendedSpeed.getValue();
				
				result = (int) ((.79 * selections * (selections2/10))/selections3);
				String value = String.valueOf(result);
				advancedCadence.setText(value);
		
		}
	
	
	/** Calls the advanced gear function for use
	 *  @param Advanced Functions
	 *  @return Advanced Functions Window 
	 */ 
	public void AdvancedFunctions(){
		Label lb_advancedFunctionTitle = new Label("   Preferred Cog" + "\n" + "by Chainring and Cadence");
		lb_advancedFunctionTitle.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 30; -fx-text-fill: #9ACD32;");
		
		HBox cadence_paneAdvanced = new HBox(10);
		HBox intendedSpeed_paneAdvanced = new HBox(10);
		HBox chainringCadence_paneAdvanced = new HBox(10);
		HBox cadence_result = new HBox(10);
		
		Label lb_chainringCadence = new Label("Chainring");
		cbChainringCadence = new ComboBox(chainringCadence);
		cbChainringCadence.setVisibleRowCount(3);
		cbChainringCadence.setValue(44); 
		chainringCadence_paneAdvanced.getChildren().addAll(lb_chainringCadence, cbChainringCadence);
		
		Label lb_intendedSpeed = new Label("Intended Riding Speed(In MPH)");
		cbIntendedSpeed = new ComboBox(intendedSpeed);
		cbIntendedSpeed.setVisibleRowCount(4);
		cbIntendedSpeed.setValue(12); 
		intendedSpeed_paneAdvanced.getChildren().addAll(lb_intendedSpeed, cbIntendedSpeed);
		
		Label lb_cadence = new Label("Cadence");
		cbCadence = new ComboBox(cadence);
		cbCadence.setVisibleRowCount(4);
		cbCadence.setValue(60); 
		cadence_paneAdvanced.getChildren().addAll(lb_cadence, cbCadence);
			
		Label lb_cadenceResult = new Label("Suggested Cog");
		advancedCadence = new TextField();
		advancedCadence.setEditable(false);
		advancedCadence.setPrefColumnCount(4);
		cadence_result.getChildren().addAll(lb_cadenceResult,advancedCadence);
		
				Stage preferredSpeedCadence = new Stage();
				preferredSpeedCadence.initModality(Modality.WINDOW_MODAL);
				 GridPane preferredSpeedCadencePane = new GridPane();
				 preferredSpeedCadencePane.setHgap(5); 
				 preferredSpeedCadencePane.setVgap(25);
				 preferredSpeedCadencePane.setPadding(new Insets(5, 10, 5, 10));
				 preferredSpeedCadencePane.add(lb_advancedFunctionTitle, 0, 0);
				 preferredSpeedCadencePane.add(intendedSpeed_paneAdvanced, 0, 2);
				 preferredSpeedCadencePane.add(cadence_paneAdvanced, 0,3);
				 preferredSpeedCadencePane.add(chainringCadence_paneAdvanced,0, 4);
				 preferredSpeedCadencePane.add(cadence_result, 2,4);
				 preferredSpeedCadencePane.add(calculateCadence, 2,3);
				 preferredSpeedCadencePane.add(clearCadence, 3,3);
				 Scene preferredSpeedCadenceScene = new Scene(preferredSpeedCadencePane, 900, 400);
				 preferredSpeedCadenceScene.getStylesheets().add("StyleTwo.css");
				 preferredSpeedCadence.setScene(preferredSpeedCadenceScene);
				 preferredSpeedCadence.setTitle("Preferred Cog");
				 preferredSpeedCadence.show();
		}
	
				
		
			
		
	/**
	 * Creates the pop-up window for the help section 
	 */
	public void openHelpSection(){
		HBox imagePane = new HBox(10);
		HBox helpSection_comment = new HBox(10);
		imagePane.setPadding(new Insets(5, 5, 5, 5));
		Image image = new Image("crankset.jpg");
		ImageView imageView2 = new ImageView(image);
	    imageView2.setFitHeight(300);
	    imageView2.setFitWidth(700);
	    imagePane.getChildren().add(imageView2);
		
		helpSectionComment = new TextArea();
		helpSectionComment.setPrefRowCount(80);
		helpSectionComment.setEditable(false);
		helpSectionComment.setPrefColumnCount(60);
		helpSectionComment.setWrapText(true);
		helpSectionComment.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 14; -fx-text-fill: green;");
		helpSection_comment.getChildren().addAll(helpSectionComment);
		
		 try {
		        Scanner s = new Scanner(new File("helpSection.txt")).useDelimiter("/");
		        while (s.hasNext()) {
		            if (s.hasNextInt()) { 
		            	helpSectionComment.appendText(s.nextInt() + " "); 
		            } else {
		            	helpSectionComment.appendText(s.next() + " "); 
		            }
		        }
		    } catch (FileNotFoundException ex) {
		        System.err.println(ex);
		    }
		Stage helpSection = new Stage();
		helpSection.initModality(Modality.WINDOW_MODAL);
		 GridPane helpSectionPane = new GridPane();
		 helpSectionPane.add(imagePane,0,0);
		 helpSectionPane.add(helpSection_comment,0,1);
		 Scene helpSectionScene = new Scene(helpSectionPane, 710, 800);
		 helpSection.setScene(helpSectionScene);
		 helpSection.setTitle("Help Section");
		 helpSection.show();
		 
	}
	
	public void openMemberLogin(){
		Stage memberLogin = new Stage();
		memberLogin.initModality(Modality.WINDOW_MODAL);
		 GridPane memberLoginPane = new GridPane();
		 Scene memberLoginScene = new Scene(memberLoginPane, 400, 200);
		 memberLogin.setScene(memberLoginScene);
		 memberLogin.setTitle("Member Login");
		 memberLogin.show();
		 
	}
	

	
	/**
	 * Clears all the Fields in the BasicCalculator
	 */
	private void clearBasic(){
		  basicResult.setText(" ");
		  basicSpeed.setText(" ");
		  basicComment.setText(" ");
	}
	
	/**
	 * Clears all the Fields in the AdvancedCalculator 
	 */
	private void clearAdvanced(){
		  advancedResult.setText(" ");
		  advancedSpeed.setText(" ");
		  advancedComment.setText(" ");
	}
	
	private void clearCadence(){
		  advancedCadence.setText(" ");
		  
	}

	private void clearSuggested(){
		  suggestedComment.setText(" ");
		}
	
	/**
	 * Connects the BicycleGearCalculator to the database
	 */
	private void initializeDB() { // Loads the driver and connects to the database
	    try {
	    	 Class.forName("com.mysql.jdbc.Driver");

	      // Connect to the local InterBase database
	      Connection conn = DriverManager.getConnection      
	        ("jdbc:mysql://localhost:3306/bicycleGearDatabase", "root", "");

	      System.out.println("Database connected\n");

	      lblStatus.setText("Database connected");

	      
	      stmt = conn.createStatement();
	    }
	    catch (Exception ex) {
	      lblStatus.setText("Connection failed: " + ex);
	    }
	  }

	public void gearPairings() {
		String selections = cbBikeExperience.getValue();
		String selections2 = cbIntendedBike.getValue();
		String selections3 = cbRidingEnvironment.getValue();
		
		//-----Commuting-----
		if(selections == "Beginner" && selections2 == "Commuting" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 1";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }    
		if(selections == "Novice" && selections2 == "Commuting" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 2";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 } 
		if(selections == "Intermediate" && selections2 == "Commuting" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 3";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Commuting" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 4";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Commuting" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 5";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Commuting" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 6";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Commuting" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 7";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Commuting" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 8";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Commuting" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 9";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Commuting" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 10";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Commuting" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 11";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Commuting" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 12";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Commuting" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 13";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Commuting" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 14";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Commuting" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 15";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Commuting" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 16";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Commuting" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 17";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Commuting" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 18";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Commuting" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 19";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Commuting" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 20";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		
		//-----Leisure-----
		if(selections == "Beginner" && selections2 == "Leisure" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 21";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }    
		if(selections == "Novice" && selections2 == "Leisure" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 22";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 } 
		if(selections == "Intermediate" && selections2 == "Leisure" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 23";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Leisure" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 24";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Leisure" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 25";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Leisure" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 26";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Leisure" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 27";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Leisure" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 28";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Leisure" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 29";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Leisure" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 30";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Leisure" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 31";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Leisure" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 32";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Leisure" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 33";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Leisure" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 34";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Leisure" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 35";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Leisure" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 36";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Leisure" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 37";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Leisure" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 38";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Leisure" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 39";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Leisure" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 40";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		
		//-----Training-----
		if(selections == "Beginner" && selections2 == "Training" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 41";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }    
		if(selections == "Novice" && selections2 == "Training" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 42";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 } 
		if(selections == "Intermediate" && selections2 == "Training" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 43";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Training" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 44";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Training" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 45";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Training" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 46";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Training" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 47";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Training" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 48";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Training" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 49";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Training" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 50";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Training" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 51";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Training" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 52";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Training" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 53";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Training" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 54";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Training" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 55";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Training" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 56";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Training" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 57";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Training" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 58";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Training" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 59";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Training" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 60";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		
		//-----TrailRiding -----
		if(selections == "Beginner" && selections2 == "Trail Riding" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 61";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }    
		if(selections == "Novice" && selections2 == "Trail Riding" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 62";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 } 
		if(selections == "Intermediate" && selections2 == "Trail Riding" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 63";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Trail Riding" && selections3 == "Flat" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 64";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Trail Riding" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 65";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Trail Riding" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 66";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Trail Riding" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 67";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Trail Riding" && selections3 == "All-Purpose Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 68";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Trail Riding" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 69";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Trail Riding" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 70";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Trail Riding" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 71";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Trail Riding" && selections3 == "Some Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 72";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Trail Riding" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 73";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Trail Riding" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 74";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Trail Riding" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 75";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Trail Riding" && selections3 == "Many Hills" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 76";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Beginner" && selections2 == "Trail Riding" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 77";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Novice" && selections2 == "Trail Riding" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 78";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Intermediate" && selections2 == "Trail Riding" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 79";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		if(selections == "Advanced" && selections2 == "Trail Riding" && selections3 == "Off-Road Trail" ){
		    String query = "SELECT * FROM gearPairings WHERE gearParingsID = 80";
		  	 try {ResultSet rs = stmt.executeQuery(query);
		  	 	if (rs.next()){suggestedComment.setText( rs.getString(2));}
		  	 		else{suggestedComment.setText("Record not found");}
		  		} catch (SQLException ex) {
		  			lblStatus.setText("Select Failed");}
		 }
		
		 }
		  	 
//-----------------------------------------------------------	

	public static void main(String[] args) {
	// TODO Auto-generated method stub
	Application.launch(args);

}
}
