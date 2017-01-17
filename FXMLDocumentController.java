/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Sam
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Button printInvoiceButton;

    @FXML
    private Button resetButton;

    @FXML
    private RadioButton newCustomer;

    @FXML
    private ToggleGroup customerType;

    @FXML
    private RadioButton regularCustomer;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private CheckBox brakes;

    @FXML
    private CheckBox tiresRotation;

    @FXML
    private CheckBox fluidCheck;

    @FXML
    private CheckBox wash;

    @FXML
    private CheckBox inspection;

    @FXML
    private CheckBox tireReplacement;

    @FXML
    private CheckBox oilChange;

    @FXML
    private ChoiceBox replacementOptions;

    @FXML
    private ChoiceBox oilChangeOptions;

    @FXML
    private Label totalCost;

    double total = 0;

    String newTotal;

    int run = 0;

    @FXML
    void onPrintInvoice(ActionEvent event) {
        String newName = name.getText();
        String newPhone = phone.getText();
        String newEmail = email.getText();
        String newAddress = address.getText();

        System.out.println("************************************");
        if (newCustomer.isSelected()) {
            System.out.println("New customer ");
        }
        if (regularCustomer.isSelected()) {
            System.out.println("Regular customer ");
        }
        System.out.println("Order: ");
        if (brakes.isSelected()) {
            System.out.print(" Brakes, ");
        }
        if (tiresRotation.isSelected()) {
            System.out.print("Tire Rotation, ");
        }
        if (fluidCheck.isSelected()) {
            System.out.print("Fluid Check, ");
        }
        if (wash.isSelected()) {
            System.out.print("Car Wash, ");
        }
        if (inspection.isSelected()) {
            System.out.print("Car inspection, ");
        }
        if (tireReplacement.isSelected()) {
            if (replacementOptions.getSelectionModel().getSelectedIndex() == 0) {
                System.out.print("Full tire replacement, ");
            } else if (replacementOptions.getSelectionModel().getSelectedIndex() == 1) {
                System.out.print("Sport tire replacement, ");
            }
        }
        if (oilChange.isSelected()) {
            if (oilChangeOptions.getSelectionModel().getSelectedIndex() == 0) {
                System.out.print("Regular oil change, ");
            } else if (oilChangeOptions.getSelectionModel().getSelectedIndex() == 1) {
                System.out.print("Synthetic oil change, ");
            }
        }
        System.out.println("\nName: " + newName + "\nPhone: " + newPhone + "\nEmail: " + newEmail + "\nAddress: " + newAddress + "\nTotal: " + newTotal);

    }

    @FXML
    void onReset(ActionEvent event) {
        brakes.setSelected(false);
        tiresRotation.setSelected(false);
        fluidCheck.setSelected(false);
        wash.setSelected(false);
        inspection.setSelected(false);

        tireReplacement.setSelected(false);
        replacementOptions.setDisable(true);
        oilChange.setSelected(false);
        oilChangeOptions.setDisable(true);

        newCustomer.setSelected(true);

        total = 0;
        newTotal = "";
        totalCost.setText("0.00");

        name.setText("");
        phone.setText("");
        email.setText("");
        address.setText("");

    }

    @FXML
    void onUpdateServiceCostRequest(ActionEvent event) {
        total = 0;

        if (brakes.isSelected()) {
            total += 30.30;
        }

        if (tiresRotation.isSelected()) {
            total += 20.20;
        }

        if (fluidCheck.isSelected()) {
            total += 10.10;
        }

        if (wash.isSelected()) {
            total += 5.05;
        }

        if (inspection.isSelected()) {
            total += 40.40;
        }

//        if (run == 0) {
//            replacementOptions.setItems(FXCollections.observableArrayList(
//                    "full tire rotation", "Sport Tires")
//            );
//            //String[] tires = new String[]{"full tire rotation", "Sport Tires"};
//            replacementOptions.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
//                public void changed(ObservableValue ov, Number value, Number new_value) {
//                    //label.setText(tires[new_value.intValue()]);
//                }
//
//            });
//
//            oilChangeOptions.setItems(FXCollections.observableArrayList(
//                    "regular oil", "synthetic oil")
//            );
//            //String[] oil = new String[]{"full oil", "half oil"};
//            oilChangeOptions.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
//                public void changed(ObservableValue ov, Number value, Number new_value) {
//                    //label.setText(tires[new_value.intValue()]);
//                }
//
//            });
//            run = 1;
//        }
        //choice boxes
        if (tireReplacement.isSelected()) {
            replacementOptions.setDisable(false);
            if (replacementOptions.getSelectionModel().getSelectedIndex() == 0) {
                total += 250.25;
            } else if (replacementOptions.getSelectionModel().getSelectedIndex() == 1) {
                total += 350.35;
            }

        } else if (!tireReplacement.isSelected()) {
            replacementOptions.setDisable(true);
        }

        if (oilChange.isSelected()) {
            oilChangeOptions.setDisable(false);
            if (oilChangeOptions.getSelectionModel().getSelectedIndex() == 0) {
                total += 15.15;
            } else if (oilChangeOptions.getSelectionModel().getSelectedIndex() == 1) {
                total += 27.27;
            }

        } else if (!oilChange.isSelected()) {
            oilChangeOptions.setDisable(true);
        }

        total = Math.round(total * 100d) / 100d;

        newTotal = Double.toString(total);

        double discountTotal = total;

        if (newCustomer.isSelected()) {
            discountTotal = discountTotal - discountTotal * .10;
        } else if (regularCustomer.isSelected()) {
            discountTotal = discountTotal - discountTotal * .15;
        }

        discountTotal = Math.round(discountTotal * 100d) / 100d;

        newTotal = Double.toString(discountTotal);

        totalCost.setText(newTotal);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        customerType.selectToggle(newCustomer);

        replacementOptions.setItems(FXCollections.observableArrayList(
                "full tire rotation", "Sport Tires"));
        //String[] tires = new String[]{"full tire rotation", "Sport Tires"};
        replacementOptions.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                //label.setText(tires[new_value.intValue()]);
                if (replacementOptions.getSelectionModel().getSelectedIndex() == 0) {
                    total += 250.25 - 350.35;
                } else if (replacementOptions.getSelectionModel().getSelectedIndex() == 1) {
                    total += 350.35 - 250.25;
                }

                //update the label for cost
                total = Math.round(total * 100d) / 100d;

                newTotal = Double.toString(total);

                //totalCost.setText(newTotal);
                double discountTotal = total;

                if (newCustomer.isSelected()) {
                    discountTotal = discountTotal - discountTotal * .10;
                } else if (regularCustomer.isSelected()) {
                    discountTotal = discountTotal - discountTotal * .15;
                }

                discountTotal = Math.round(discountTotal * 100d) / 100d;

                newTotal = Double.toString(discountTotal);

                totalCost.setText(newTotal);
            }

        });

        oilChangeOptions.setItems(FXCollections.observableArrayList(
                "regular oil", "synthetic oil")
        );
        //String[] oil = new String[]{"full oil", "half oil"};
        oilChangeOptions.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value) {
                //label.setText(tires[new_value.intValue()]);

                if (oilChangeOptions.getSelectionModel().getSelectedIndex() == 0) {
                    total += 15.15 - 27.27;
                } else if (oilChangeOptions.getSelectionModel().getSelectedIndex() == 1) {
                    total += 27.27 - 15.15;
                }

                //update the label for cost
                total = Math.round(total * 100d) / 100d;

                newTotal = Double.toString(total);

                //totalCost.setText(newTotal);
                double discountTotal = total;

                if (newCustomer.isSelected()) {
                    discountTotal = discountTotal - discountTotal * .10;
                } else if (regularCustomer.isSelected()) {
                    discountTotal = discountTotal - discountTotal * .15;
                }

                discountTotal = Math.round(discountTotal * 100d) / 100d;

                newTotal = Double.toString(discountTotal);

                totalCost.setText(newTotal);
            }
        });

        replacementOptions.setItems(FXCollections.observableArrayList(
                "full tire rotation", "Sport Tires"));
        replacementOptions.getSelectionModel().select(0);
        oilChangeOptions.setItems(FXCollections.observableArrayList(
                "regular oil", "synthetic oil"));
        oilChangeOptions.getSelectionModel().select(0);

        
        
        totalCost.setText("0.00");
    }

}
