package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class MainController implements Initializable  {
	@FXML
	private TextField txtBefore;
	@FXML
	private TextField txtDiscount;
	@FXML
	private TextField txtAfter;
	@FXML
	private TextField txtYouSaved;
	
	private float before = 0;
	private float discount = 0;
	private float after = 0;
	private float you_save = 0;
	
	public int wasActive = 0;
	public int isActive = 0;

	FloatProperty price = new SimpleFloatProperty();
	FloatProperty rate = new SimpleFloatProperty();
	FloatProperty newPrice = new SimpleFloatProperty();
	FloatProperty  saved = new SimpleFloatProperty();
	
	StringConverter<Number> converter;

	public float getBefore() {
		return before;
	}

	public void setBefore(float before) {
		this.before = before;
		price.set(before);
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
		rate.set(discount);
	}

	public float getAfter() {
		return after;
	}

	public void setAfter(float after) {
		this.after = after;
		newPrice.set(after);
	}

	public float getYou_save() {
		return you_save;
	}

	public void setYou_save(float you_save) {
		this.you_save = you_save;
		saved .set(you_save);
	}
		
	public void focusUpdater(TextField txt,int id) {
		
		txt.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(
		    		ObservableValue<? extends Boolean> arg0,
		    		Boolean oldPropertyValue, 
		    		Boolean newPropertyValue)
		    {
		        if (newPropertyValue)
		        {
		        	isActive = id;
		        }
		        else
		        {
		        	wasActive = id;

		        }
		    }
		});	
		
	}

	public void calculate() {
		
		   if((isActive == 1 && wasActive == 2) 
				   || (isActive == 2 && wasActive == 1)) {
			   setYou_save( getBefore() * (getDiscount() / 100));
			   setAfter( getBefore() - getYou_save());
		   }
		   
		   else if((isActive == 1 && wasActive == 3) 
				   || (isActive == 3 && wasActive == 1)) {
			   setYou_save( getBefore() - getAfter());
			   setDiscount( (getYou_save() / getBefore()) * 100);
		   }
		   
		   else if((isActive == 1 && wasActive == 4) 
				   || (isActive == 4 && wasActive == 1)) {
			  setDiscount( (getYou_save() / getBefore()) * 100);
		      setAfter( getBefore() - getYou_save());
		   }
		   
		   else if((isActive == 2 && wasActive == 4) 
				   || (isActive == 4 && wasActive == 2)) {
			   setBefore( (100 * getYou_save()) / getDiscount());
			   setAfter( getBefore() - getYou_save());
		   }
		   
		   else if((isActive == 2 && wasActive == 3) 
				   || (isActive == 3 && wasActive == 2)) {
			   setBefore( getAfter() / (1 - (getDiscount() / 100)));
			   setYou_save( getBefore() - getAfter());
		   }
		   
		   else if((isActive == 3 && wasActive == 4) 
				   || (isActive == 4 && wasActive == 3)) {
			   setBefore(getAfter() + getYou_save());
			   setDiscount( (getYou_save() / getBefore()) * 100);
		   }

	}
	
	public void getValue() {
		
		try {
			
			if(isActive == 1) {
				setBefore(Float.parseFloat(txtBefore.getText()));
			}
			else if(isActive == 2) {
				setDiscount(Float.parseFloat(txtDiscount.getText()));
			}
			else if(isActive == 3) {
				setAfter(Float.parseFloat(txtAfter.getText()));
			}
			else if(isActive == 4) {
				setYou_save(Float.parseFloat(txtYouSaved.getText()));

			}
								
		} catch(NumberFormatException ex) {

		}
		
	}
	
	public void setListener(TextField txt) {
		
		txt.textProperty().addListener(e -> {
			
		    getValue();				
			calculate();
			//printResult();
			
		});
		
	}
	
	public void resetOnAction() {
		
		setBefore(0);
		setAfter(0);
		setDiscount(0);
		setYou_save(0);
		
		txtBefore.clear();
		txtDiscount.clear();
		txtAfter.clear();
		txtYouSaved.clear();
		
		wasActive = 0;
		isActive = 0;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) throws NumberFormatException  {
		
		converter = new NumberStringConverter();

		Bindings.bindBidirectional(txtBefore.textProperty(), price,converter);
		Bindings.bindBidirectional(txtDiscount.textProperty(), rate,converter);
		Bindings.bindBidirectional(txtAfter.textProperty(), newPrice,converter);
		Bindings.bindBidirectional(txtYouSaved.textProperty(), saved,converter);

		focusUpdater(txtBefore,1);
		focusUpdater(txtDiscount,2);
		focusUpdater(txtAfter,3);
		focusUpdater(txtYouSaved,4);
		
		setListener(txtBefore);
		setListener(txtDiscount);
		setListener(txtAfter);
		setListener(txtYouSaved);
	}
}