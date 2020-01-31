/**
 * This is the UserInterface class, which displays the information to the user.
 *
 * @author Jhordan Figueroa
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserInterface {
    //Instance Variables
    private JTextField searchTextField;
    private JLabel t2;
    private JLabel ETF1 = new JLabel();
    private JLabel ETF2 = new JLabel();
    private JLabel ETF3 = new JLabel();
    private JButton deleteFromList;
    private JButton addToList;
    private JList list;
    private DefaultListModel listModel;

    public UserInterface(Container pane) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //This divides the frame into a grid layout for displaying information with different panels
        JPanel outerPanel = new JPanel(new GridLayout(4, 1));
        outerPanel.setBackground(Color.BLACK);
        JPanel displayPanel = new JPanel(new FlowLayout());
        displayPanel.setBackground(Color.BLACK);
        JPanel innerTopPanel = new JPanel(new GridLayout(3, 1));
        innerTopPanel.setBackground(Color.BLACK);
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(Color.BLACK);
        JPanel listPanel = new JPanel(new GridLayout(1,1));
        listPanel.setBackground(Color.GREEN);

        //Create Components

        JButton displayTradeData = new JButton("DisplayTrades"); //Creates DisplayTrade Button
        displayTradeData.setFont(displayTradeData.getFont().deriveFont(Font.BOLD));
        displayTradeData.setBackground(Color.RED);
        displayTradeData.setOpaque(true);
        displayTradeData.setBorderPainted(false);

        addToList = new JButton("Add"); //Creates Add Button
        deleteFromList = new JButton("Remove"); //Creates Remove Button

        searchTextField = new JTextField("Search"); //Creates Search ETF/Stock Field
        // remove existing text from search field when clicked into
        searchTextField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                searchTextField.setText("");
            }
        });

        searchTextField.setBounds(100, 100, 200, 30);

        Border border = BorderFactory.createTitledBorder("Your List");
        listPanel.setBorder(border);

        listModel = new DefaultListModel();

        list = new JList(listModel); //Creates List Feature
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(1);
        JScrollPane listScrollPane = new JScrollPane(list); //Creates ScrollPanel
        listScrollPane.setBorder(border);

        //Add the components together
        pane.add(outerPanel);
        outerPanel.add(displayPanel);
        outerPanel.add(innerTopPanel);
        outerPanel.add(searchPanel);
        displayPanel.add(displayTradeData);
//        displayPanel.add(listPanel);
        innerTopPanel.add(ETF1);
        innerTopPanel.add(ETF2);
        innerTopPanel.add(ETF3);
        searchPanel.add(searchTextField);
        searchPanel.add(addToList);
        searchPanel.add(deleteFromList);
        outerPanel.add(listScrollPane);

        //ActionListeners
        addToList.addActionListener(getAddButtonAction()); //Implements searching for stock/etf feature and adding
        //the list.
        deleteFromList.addActionListener(removeStock()); //Implements removeStock listener so user can remove stock/etf
        //from the list
        displayTradeData.addActionListener(displayTradeButtonAction()); //Implements the displayTradeButtonAction
        //listener so the user can see what the technical analysis indicates.
    }

    //Allows the user to add any stock/etf to his list by searching for the stock/etf using the API
    private ActionListener getAddButtonAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String securitySymbol = searchTextField.getText();

                if (securitySymbol.equals("") || securityInList(securitySymbol)) {
                    Toolkit.getDefaultToolkit().beep();
                    return;
                }
                int index = list.getSelectedIndex();
                if (index == -1) {
                    index = 0;
                } else {
                    index++;
                }

                WebsiteDataReader data = new WebsiteDataReader();
                Quote quote = data.getData(securitySymbol);
                listModel.insertElementAt(quote.toString(), index);
                searchTextField.setText("");
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        };
    }

    //Checks to see if the list already contains the security the user is searching for
    private boolean securityInList(String securityName) {
        return listModel.contains(securityName);
    }

    //Displays the technical analysis on the trades for ETF's: SPY, QQQ, and DIA using WebsiteDataReader, AutoTrader,
    //and Quote classes.
    private ActionListener displayTradeButtonAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WebsiteDataReader data = new WebsiteDataReader();
                Quote SPY = data.getData("SPY");
                boolean tradeSPYResult = AutoTrader.decideTrade(SPY);
                Quote QQQ = data.getData("QQQ");
                boolean tradeQQQResult = AutoTrader.decideTrade(QQQ);
                Quote DIA = data.getData("DIA");
                boolean tradeDIAResult = AutoTrader.decideTrade(DIA);

                ETF1.setText(getTextForTradeResult(SPY, tradeSPYResult));
                ETF1.setForeground(tradeSPYResult ? Color.GREEN : Color.RED);

                ETF2.setText(getTextForTradeResult(QQQ, tradeQQQResult));
                ETF2.setForeground(tradeQQQResult ? Color.GREEN : Color.RED);

                ETF3.setText(getTextForTradeResult(DIA, tradeDIAResult));
                ETF3.setForeground(tradeDIAResult ? Color.GREEN : Color.RED);
            }
        };
    }

    //Helper method to display the trades, used in displayTradeButtonAction
    private String getTextForTradeResult(Quote quote, boolean success) {
        String text = " | Trade with " + quote.getETF();
        String result = success ? " was executed " : " was NOT executed ";
        String endText = "Based on Stochastic Indicator.";
        return quote.toString() + text + result + endText;
    }

    //Allows the user to remove a highlighted stock/etf from the list
    private ActionListener removeStock() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = list.getSelectedIndex();
                listModel.remove(index);
                int size = listModel.getSize();
                if (size == 0) {
                    deleteFromList.setEnabled(false);
                } else {
                    if (index == listModel.getSize()) {
                        index--;
                    }
                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                }
            }
        };
    }

    //Creates the GUI with the required information by Swing
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Financiers Team 30");
        new UserInterface(frame.getContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 400);
        frame.setVisible(true);
    }
}