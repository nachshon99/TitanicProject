import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel {
    private JComboBox<String> survivedComboBox;
    private List<Passenger> passengerList = new ArrayList<>();
    private int passengerId;
    private int survived;
    private int pClass;
    private String name = "";
    private String sex;
    private int age;
    private int sibSp;
    private int parch;
    private String ticket;
    private double fare;
    private String cabin;
    private String embarked;

    public MainPanel (int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file
        BufferedReader reader = null;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null){
                if(Character.isDigit(line.charAt(0))) {
                    String [] elements = line.split(",");
                    for (int i = 0; i < elements.length; i++){
                        switch (i){
                            case Constants.INDEX_0: {
                                passengerId = parse(i,passengerId,elements);
                                break;
                            }
                            case Constants.INDEX_1: {
                                survived = parse(i,survived,elements);
                                break;
                            }
                            case Constants.INDEX_2: {
                                pClass = parse(i,pClass,elements);
                                break;
                            }
                            case Constants.INDEX_3: {
                            }
                            case Constants.INDEX_4: {
                                name += elements[i];
                                break;
                            }
                            case Constants.INDEX_5: {
                                sex = elements[i];
                                break;
                            }
                            case Constants.INDEX_6: {
                                age = parse(i,age,elements);
                                break;
                            }
                            case Constants.INDEX_7: {
                                sibSp = parse(i,sibSp,elements);
                                break;
                            }
                            case Constants.INDEX_8: {
                                parch = parse(i,parch,elements);
                                break;
                            }
                            case Constants.INDEX_9: {
                                ticket = elements[i];
                                break;
                            }
                            case Constants.INDEX_10: {
                                fare = Double.parseDouble(elements[i]);
                                break;
                            }
                            case Constants.INDEX_11: {
                                cabin = elements[i];
                                break;
                            }
                            case Constants.INDEX_12: {
                                embarked = elements[i];
                                break;
                            }
                        }

                    }
                    Passenger passenger = new Passenger(passengerId,survived,pClass,name,sex,age,sibSp,parch,ticket,fare,cabin,embarked);
                    passengerList.add(passenger);
                    passengerId = 0;
                    survived = 0;
                    pClass = 0;
                    name = "";
                    sex = "";
                    age = 0;
                    sibSp = 0;
                    parch = 0;
                    ticket = "";
                    fare = 0;
                    cabin = "";
                    embarked = "";
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.setLayout(null);
        this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);
        JLabel survivedLabel = new JLabel("Passenger Class: ");
        survivedLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.add(survivedLabel);
        this.survivedComboBox = new JComboBox<>(Constants.PASSENGER_CLASS_OPTIONS);
        this.survivedComboBox.setBounds(survivedLabel.getX() + survivedLabel.getWidth() , survivedLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.add(this.survivedComboBox);
        this.survivedComboBox.addActionListener((e) -> {
            //do whatever you want on change

        });
    }

    public static int parse(int i, int element, String [] elements){
        try {
            element = Integer.parseInt(elements[i]);
        }catch (NumberFormatException e){
        }
        return element;
    }

}
