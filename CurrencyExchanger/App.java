import java.io.IOException;

import org.apache.log4j.*;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.round;

public class App {


    public static String[][] search(String URL) throws IOException {

        String[][] temp1 = new String[0][0];
        Response response = Jsoup.connect(URL)
                .ignoreContentType(true)
                .userAgent("Chrome")
                .referrer("http://www.google.com")
                .timeout(120000)
                .followRedirects(true)
                .execute();

        Document document = response.parse();
        try {
            Element table = document.select("table.table.table-sm").get(0); //select the first table.

            Elements rows = table.select("tr");
            Elements cols = table.select("td");

            String[][] temp = new String[rows.size()][cols.size()];

            for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
                Element row = rows.get(i);
                cols = row.select("td");
                temp[i - 1][0] = cols.get(0).text();
                temp[i - 1][1] = cols.get(1).text();
                temp[i - 1][2] = cols.get(2).text();
                temp[i - 1][3] = cols.get(3).text();
            }


            return temp;
        }
        catch (Exception exp)
        {
            //JOptionPane.showMessageDialog(frame, "No internet connection!");
            exp.printStackTrace();
        }
        return temp1;
    }


    public static void log4jConfig()
    {
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "%-7p %d %c %x - %m%n";
        layout.setConversionPattern(conversionPattern);

        // creates console appender
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();

        // creates file appender
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile("applog.txt");
        fileAppender.setLayout(layout);
        fileAppender.setImmediateFlush(true);
        fileAppender.setAppend(false);

        fileAppender.activateOptions();

        // configures the root logger
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(fileAppender);

    }
    public static class Gui extends Frame {
        private String[][] tablica;

        private JFrame frame;
        private JPanel panel;
        private String[] Cities = new String[]{"Kraków", "Warszawa",
                "Katowice", "Wrocław", "Poznań"};
        private String[] SellBuy = new String[]{"Sell", "Buy"};
        private JComboBox<String> CityList = new JComboBox<>(Cities);
        private JComboBox<String> chooseSellBuy = new JComboBox<>(SellBuy);

        private JScrollPane scroll;
        private JPanel table_panel;
        private JTextField money;
        private JLabel amountFrom;
        private JLabel amountTo;
        private JLabel amount;
        private JLabel firstStep;
        private JButton search;
        private String cityName="Kraków";
        private String buySellChoice="Sell";
        private ArrayList<String> currency = new ArrayList<String>();
        private JComboBox<String> currencyChooseBoxFrom;
        private JComboBox<String> currencyChooseBoxTo;
        private JPanel panelBottom;
        private JButton exchange;
        private Logger logger;

        private String CurrencyNameFrom="PLN";
        private String CurrencyNameTo="PLN";
        private JTextField exchangedAmountText=new JTextField(15);


        public Gui() {
            logger= Logger.getLogger(App.class);
            log4jConfig();
            frame = new JFrame("Exchange office");
            CityList.setSize(new Dimension(20, 10));
            JPanel panel = new JPanel();
            JPanel panelNorth = new JPanel();
            //firstStep = new JLabel("To show current exchange rate select the city, buy or sell and press Search: ");
            firstStep = new JLabel("To show current exchange rate: ");
            chooseSellBuy.setSize(new Dimension(20, 10));
            search = new JButton("Search");
            //panel.setLayout(new GridLayout(2, 0));
            panel.add(firstStep);
            panel.add(CityList);
            panel.add(chooseSellBuy);
            panel.add(search);


            frame.getContentPane().add(BorderLayout.NORTH, panel);
            frame.setSize(900, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            CityClick cityClick = new CityClick();
            CityList.addActionListener(cityClick);
            BuySellClick buySellClick = new BuySellClick();
            chooseSellBuy.addActionListener(buySellClick);
            SearchButton searchButton = new SearchButton();
            search.addActionListener(searchButton);
        }

        public void ShowCurrency()
        {
            if (panelBottom == null) {
                panelBottom = new JPanel();
            } else {
                panelBottom.removeAll();
                frame.remove(panelBottom);
                panelBottom = new JPanel();
            }

            amountFrom = new JLabel("From: ");
            amountTo = new JLabel("To: ");
            amount = new JLabel("Amount: ");
            money = new JTextField("0",10);
            exchange=new JButton("Exchange");

            money.setHorizontalAlignment(SwingConstants.RIGHT);
            money.setSize(new Dimension(20, 10));


            currencyChooseBoxFrom= new JComboBox<>(currency.stream().toArray(String[]::new));
            currencyChooseBoxTo= new JComboBox<>(currency.stream().toArray(String[]::new));
            panelBottom.add(amountFrom);
            panelBottom.add(currencyChooseBoxFrom);
            panelBottom.add(amountTo);
            panelBottom.add(currencyChooseBoxTo);
            panelBottom.add(amount);
            panelBottom.add(money);
            panelBottom.add(exchange);


            frame.getContentPane().add(BorderLayout.SOUTH, panelBottom);
            frame.setVisible(true);
            currency.clear();

            ExchangeMoney exchangeClick = new ExchangeMoney();
            exchange.addActionListener(exchangeClick);

            CurrencyFrom currencyFromClick=new CurrencyFrom();
            currencyChooseBoxFrom.addActionListener(currencyFromClick);

            CurrencyTo currencyToClick = new CurrencyTo();
            currencyChooseBoxTo.addActionListener(currencyToClick);
            //Action listenery



        }
        public void ShowTable(String[][] tablica,boolean sell) {
            if (table_panel == null) {
                table_panel = new JPanel(new BorderLayout());
            } else {
                table_panel.removeAll();
            }

            table_panel.setLayout(new GridLayout(tablica.length, tablica[0].length));
            currency.add(0,"PLN");
            for (int i = 0; i < tablica.length - 1; i++) {
                JTextField temp1 = new JTextField(tablica[i][1]);
                temp1.setPreferredSize(new Dimension(40, 20));
                temp1.setHorizontalAlignment(JTextField.CENTER);
                table_panel.add(temp1);

                JTextField text = new JTextField();
                text.setText(tablica[i][0]);
                currency.add(tablica[i][0].substring(2)); //dodanie do array listy żeby wyświetlić w combobox
                text.setPreferredSize(new Dimension(20, 20));
                text.setEditable(false);
                text.setHorizontalAlignment(JTextField.CENTER);

                table_panel.add(text);

                //for (int j = 2; j < 4; j++) {
                if(sell)
                {
                    JTextField text1 = new JTextField();
                    text1.setText(tablica[i][3]);
                    text1.setPreferredSize(new Dimension(20, 20));
                    text1.setEditable(false);
                    text1.setHorizontalAlignment(JTextField.CENTER);
                    table_panel.add(text1);
                }
                else
                {
                    JTextField text1 = new JTextField();
                    text1.setText(tablica[i][2]);
                    text1.setPreferredSize(new Dimension(20, 20));
                    text1.setEditable(false);
                    text1.setHorizontalAlignment(JTextField.CENTER);
                    table_panel.add(text1);

                }

            }


            if (scroll == null) {
                scroll = new JScrollPane(table_panel);
            }
            else {
                scroll.removeAll();
                frame.remove(scroll);
                scroll = new JScrollPane(table_panel);
            }

            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            frame.getContentPane().add(BorderLayout.CENTER, scroll);
            frame.setVisible(true);
            logger.info("Table printed.");

        }


        private class SearchButton implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                boolean sell=true;
                CurrencyNameFrom="PLN";
                CurrencyNameTo="PLN";
                logger.info("Search pressed.");
                if(buySellChoice.equals("Buy")) {
                    sell = false;
                    logger.info("You chose to buy");
                }
                else {
                    sell=true;
                    logger.info("You chose to sell");
                }

                if (cityName.equals("Kraków")) {
                    try {
                        logger.info("You chose Kraków.");
                        tablica = search("https://kantor.live/kantory/krakow/466986-kantor-baksy");
                        logger.info("Parsing URL");
                        ShowTable(tablica,sell);
                        ShowCurrency();

                    } catch (IOException e) {
                        logger.info("No internet connection");
                        JOptionPane.showMessageDialog(frame, "No internet connection!");
                        e.printStackTrace();
                    }
                } else if (cityName.equals("Warszawa")) {
                    try {
                        logger.info("You chose Warszawa.");
                        tablica = search("https://kantor.live/kantory/warszawa/254895-kantor-centrum");
                        logger.info("Parsing URL");
                        ShowTable(tablica,sell);
                        ShowCurrency();
                    } catch (IOException e) {
                        logger.info("No internet connection");
                        JOptionPane.showMessageDialog(frame, "No internet connection!");
                        e.printStackTrace();
                    }
                } else if (cityName.equals("Katowice")) {
                    try {
                        logger.info("You chose Katowice.");
                        tablica = search("https://kantor.live/kantory/katowice/577888-kantor-exg-stawowa");
                        logger.info("Parsing URL");
                        ShowTable(tablica,sell);
                        ShowCurrency();
                    } catch (IOException e) {
                        logger.info("No internet connection");
                        JOptionPane.showMessageDialog(frame, "No internet connection!");
                        e.printStackTrace();
                    }
                } else if (cityName.equals("Wrocław")) {
                    try {
                        logger.info("You chose Wrocław.");
                        tablica = search("https://kantor.live/kantory/wroclaw/312847-kantor-dorex-tesco-ikea");
                        logger.info("Parsing URL");
                        ShowTable(tablica,sell);
                        ShowCurrency();
                    } catch (IOException e) {
                        logger.info("No internet connection");
                        JOptionPane.showMessageDialog(frame, "No internet connection!");
                        e.printStackTrace();
                    }
                } else if (cityName.equals("Poznań")) {
                    try {
                        logger.info("You chose Poznań.");
                        tablica = search("https://kantor.live/kantory/poznan/203522-kantor-hirex");
                        logger.info("Parsing URL");
                        ShowTable(tablica,sell);
                        ShowCurrency();
                    } catch (IOException e) {
                        logger.info("No internet connection");
                        JOptionPane.showMessageDialog(frame, "No internet connection!");
                        e.printStackTrace();
                    }
                }
            }
        }


        private class BuySellClick implements ActionListener {
            //bookList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> combo = (JComboBox<String>) event.getSource();
                buySellChoice = (String) combo.getSelectedItem();
                logger.info(buySellChoice + " pressed.");
            }
        }
        private class CityClick implements ActionListener {
            //bookList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> combo = (JComboBox<String>) event.getSource();
                //String selectedCity = (String) combo.getSelectedItem();
                cityName = (String) combo.getSelectedItem();
                logger.info(cityName + " pressed.");
            }
        }

        private class CurrencyFrom implements ActionListener {
            //bookList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> combo = (JComboBox<String>) event.getSource();
                CurrencyNameFrom = (String) combo.getSelectedItem();
                logger.info(CurrencyNameFrom + " pressed.");
            }
        }

        private class CurrencyTo implements ActionListener {
            //bookList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> combo = (JComboBox<String>) event.getSource();
                CurrencyNameTo = (String) combo.getSelectedItem();
                logger.info(CurrencyNameTo + " pressed.");
            }
        }

        private class ExchangeMoney implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(CurrencyNameFrom);
                logger.info("Exchange pressed.");
                double currencyFromExchangeRate=1;
                double currencyToExchangeRate=1;
                double amountOfMoneyToExchange;
                double exchangedAmount;

                try {
                    if (money.getText().contains(",")) {

                        amountOfMoneyToExchange = Double.parseDouble(money.getText().replace(",", "."));
                    } else amountOfMoneyToExchange = Double.parseDouble(money.getText());

                    if (CurrencyNameFrom.equals(CurrencyNameTo)) exchangedAmount = amountOfMoneyToExchange;

                    for (int i = 0; i < tablica.length - 1; i++) {

                        if (tablica[i][0].equals("1 " + CurrencyNameFrom)) {
                            currencyFromExchangeRate = Double.parseDouble(tablica[i][2].substring(0, tablica[i][2].length() - 4)); // to erase currency name

                        }
                        if (tablica[i][0].equals("1 " + CurrencyNameTo)) {
                            currencyToExchangeRate = Double.parseDouble(tablica[i][3].substring(0, tablica[i][3].length() - 4));
                        }

                    }
                    if (CurrencyNameFrom == "PLN" && CurrencyNameTo != "PLN")
                        exchangedAmount = amountOfMoneyToExchange / currencyToExchangeRate;
                    else if (CurrencyNameTo == "PLN" && CurrencyNameFrom != "PLN")
                        exchangedAmount = amountOfMoneyToExchange * currencyFromExchangeRate;
                    else exchangedAmount = amountOfMoneyToExchange * currencyFromExchangeRate / currencyToExchangeRate;

                    exchangedAmountText.setText(String.format("%.2f", exchangedAmount) + " " + CurrencyNameTo);

                    logger.info("Value shown.");
                    exchangedAmountText.setHorizontalAlignment(SwingConstants.RIGHT);
                    exchangedAmountText.setEditable(false);
                    panelBottom.add(exchangedAmountText);
                }
                catch (Exception exc)
                {
                    logger.info("Wrong input.");
                    JOptionPane.showMessageDialog(frame, "Wrong input!");
                }

                frame.getContentPane().add(BorderLayout.SOUTH, panelBottom);
                frame.setVisible(true);


            }
        }


    }
        public static void main(String[] args) throws IOException {
            Gui App = new Gui();
    }


}
