package application;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController2 implements Initializable{

	   @FXML
	    public ComboBox<String> comboBox;
	  
	   @FXML
	    public ListView<String> listview;
	   @FXML
	    public Label  lab1;
	   
	   @FXML
	   TreeView <String> treeView;
	   
	   @FXML
	   private Button btn1;
	   
	   @FXML
	   private Button btn2;
	   
	   @FXML
	    public ListView listview2;
	  
	   
	   
	   @FXML
	   Label lblDisplay;
	    
	    ObservableList<String> list = FXCollections.observableArrayList("1","2","3","4");
		
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
	    	
	    	comboBox.setItems(list);
	    	listview.setItems(list);
	    	listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    	
	    	TreeItem<String> root = new TreeItem<>("Root");
	    	root.setExpanded(true);
	    	
	    	TreeItem<String> nodeA = new TreeItem<>("Node A");
	    	TreeItem<String> nodeB = new TreeItem<>("Node B");
	    	TreeItem<String> nodeC = new TreeItem<>("Node C");
	    	
	    	root.getChildren().add(nodeA);
	    	root.getChildren().add(nodeB);
	    	root.getChildren().add(nodeC);
	    	nodeA.setExpanded(true);

	    	TreeItem<String> nodeA1 = new TreeItem<>("Node A1");
	    	TreeItem<String> nodeB1 = new TreeItem<>("Node B1");
	    	TreeItem<String> nodeC1 = new TreeItem<>("Node C1");
	    	
	    	nodeA.getChildren().addAll(nodeA1,nodeB1,nodeC1);
	    	
	    	treeView.setRoot(root);
			
		}
	    
	    public void mouseClick(MouseEvent event) {
	    	if(event.getClickCount() == 2) {
	    	
	    	TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
	    	System.out.println(item.getValue());}
	    }
	    
	    public void ComboChanged(ActionEvent event) {
	    	
	    	
	    	lblDisplay.setText(comboBox.getValue());
	    	
	    }
	    
       public void buttonAction(ActionEvent event) {
	    	
    	   //listview.getItems().addAll("5","6","7","8");
    	   
    	   ObservableList<String> nums;
    	   nums = listview.getSelectionModel().getSelectedItems();
    	   for(String num:nums ) {
    		   
    		   System.out.println(num);
    	   }
	    	    	
	    }
       
       public void buttonAction1(ActionEvent event) {
    	   
    	   FileChooser fc = new FileChooser();
    	   fc.setInitialDirectory(new File ("C:\\Users\\user\\UMT\\Digital Logic Design"));
    	   fc.getExtensionFilters().addAll(
    			   new ExtensionFilter("PDF Files", "*.pdf"));
    	   File selectedFile = fc.showOpenDialog(null);
    	   
    	   if( selectedFile != null) {
    		   listview2.getItems().add(selectedFile.getAbsolutePath());
    	   } else {
    		   
    		   System.out.println("File is not valid");
    	   }
    	   
       }
       
       public void buttonAction2(ActionEvent event) {
    	   
    	   FileChooser fc = new FileChooser();
    	   fc.setInitialDirectory(new File ("C:\\Users\\user\\UMT\\Digital Logic Design"));
    	   fc.getExtensionFilters().addAll(
    			   new ExtensionFilter("PDF Files", "*.pdf"));
    	   List<File> selectedFiles = fc.showOpenMultipleDialog(null);
    	   
    	   
    	   
    	   if( selectedFiles != null) {
    		   for(int i =0; i < selectedFiles.size(); i++) {
    		   listview2.getItems().add(selectedFiles.get(i).getAbsolutePath());
    	   } 
    	 }
    	   else {
    		   
    		   System.out.println("File is not valid");
    	   }
    	   
       }
		

}
