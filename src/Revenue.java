import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Revenue extends JFrame
{
    private static final long serialVersionUID = 8630455390396412928L;
    private Box mainBox;
    private List<JTextField> revenues = new ArrayList<JTextField>();
    private InvestmentProject investmentProject;
    private List<List<JTextField>> sales;
    private List<List<JTextField>> products;

    public Revenue(List<List<JTextField>> sales, List<List<JTextField>> products, final InvestmentProject investmentProject)
    {
        super("Revenues");
        this.investmentProject = investmentProject;
        this.sales = sales;
        this.products = products;
        mainBox = Box.createVerticalBox();
        JLabel salesLabel = new JLabel("Revenues from sales");
        mainBox.add(salesLabel);
        createRevenues();
        createTotalVat();
        createTotalVatRevenue();
        createSaveBox();
        setContentPane(mainBox);
    }

    private void createRevenues()
    {
        createTopBox();
        for(int j = 0; j < products.size(); j++)
        {
            Box box = Box.createHorizontalBox();
            box.add(Box.createHorizontalStrut(6));

            List<JTextField> productTextFields = products.get(j);
            List<JTextField> salesTextFields = sales.get(j);
            createTextField(box, productTextFields.get(0).getText(), false, revenues);
            String until = "rub/" + productTextFields.get(1).getText();
            createTextField(box, until, false, revenues);

            Integer totalProduct = 0;
            for(int index = 2; index < productTextFields.size(); index++)
            {
                Integer numProducts = Integer.valueOf(productTextFields.get(index).getText());
                Integer numSales = Integer.valueOf(salesTextFields.get(index).getText());
                Integer value = numProducts * numSales;
                totalProduct += value;
                createTextField(box, value.toString(), true, revenues);
            }
            createTextField(box, totalProduct.toString(), true, revenues);
            mainBox.add(Box.createHorizontalStrut(3));
            mainBox.add(box);
        }
    }

    private void createTopBox()
    {
        Box topBox = Box.createHorizontalBox();
        createTextField(topBox, "Revenues from sales", false, revenues);
        createTextField(topBox, "rub", false, revenues);

        for(int index = 0; index < investmentProject.getNumberPlanning(); index++)
        {
            Integer numQuarter = index + 1;
            String text= numQuarter.toString() + " quarter";
            createTextField(topBox, text, false, revenues);
        }
        createTextField(topBox, "Total", false, revenues);

        mainBox.add(Box.createHorizontalStrut(3));
        mainBox.add(topBox);
    }

    private void createTotalVat()
    {
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalStrut(6));
        createTextField(box, "Total revenues net of VAT", false, revenues);
        createTextField(box, "rub", false, revenues);

        List<List<Double>> productVat = new ArrayList<List<Double>>();
        for(int j = 0; j < products.size(); j++)
        {
            List<Double> vatValues = new ArrayList<Double>();
            List<JTextField> productTextFields = products.get(j);
            List<JTextField> salesTextFields = sales.get(j);

            for(int index = 2; index < productTextFields.size(); index++)
            {
                Integer numProducts = Integer.valueOf(productTextFields.get(index).getText());
                Integer numSales = Integer.valueOf(salesTextFields.get(index).getText());
                int vatValue = numProducts * numSales;
                double value = vatValue / (1 + (investmentProject.getRateVAT().doubleValue() / 100));
                vatValues.add(value);
            }
            productVat.add(vatValues);
        }
        double totalValue = 0;
        for(int index = 0; index < products.size(); index++)
        {
            double value = 0;
            for(int indexVat = 0; indexVat < productVat.size(); indexVat++)
            {
                value += productVat.get(indexVat).get(index);
                totalValue += value;
            }
            createTextField(box, String.valueOf(value), true, revenues);
        }
        createTextField(box, String.valueOf(totalValue), true, revenues);

        mainBox.add(Box.createHorizontalStrut(3));
        mainBox.add(box);
    }

    private void createTotalVatRevenue()
    {
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalStrut(6));
        createTextField(box, "VAT revenue", false, revenues);
        createTextField(box, "rub", false, revenues);

        List<List<Double>> productVat = new ArrayList<List<Double>>();
        for(int j = 0; j < products.size(); j++)
        {
            List<Double> vatValues = new ArrayList<Double>();
            List<JTextField> productTextFields = products.get(j);
            List<JTextField> salesTextFields = sales.get(j);

            for(int index = 2; index < productTextFields.size(); index++)
            {
                Integer numProducts = Integer.valueOf(productTextFields.get(index).getText());
                Integer numSales = Integer.valueOf(salesTextFields.get(index).getText());
                int sumRevenues = numProducts * numSales;
                double value = sumRevenues -  sumRevenues / (1 + (investmentProject.getRateVAT().doubleValue() / 100));
                vatValues.add(value);
            }
            productVat.add(vatValues);
        }
        double totalValue = 0;
        for(int index = 0; index < products.size(); index++)
        {
            double value = 0;
            for(int indexVat = 0; indexVat < productVat.size(); indexVat++)
            {
                value += productVat.get(indexVat).get(index);
                totalValue += value;
            }
            createTextField(box, String.valueOf(value), true, revenues);
        }
        createTextField(box, String.valueOf(totalValue), true, revenues);
        mainBox.add(Box.createHorizontalStrut(3));
        mainBox.add(box);
    }

    private void createSaveBox()
    {
        Box saveBox = Box.createHorizontalBox();
        JButton ok = new JButton("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame productCosts = new ProductCosts(investmentProject);
                    productCosts.setSize(new Dimension(1000, 500));
                    productCosts.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    productCosts.setVisible(true);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(rootPane, "Is invalid value");
                }

            }
        });
        saveBox.add(ok);
        mainBox.add(Box.createVerticalStrut(22));
        mainBox.add(saveBox);
    }

    private void createTextField(Box box, String text, boolean isEnabled, List<JTextField> fields)
    {
        JTextField nameField = new JTextField(3);
        nameField.setEnabled(isEnabled);
        nameField.setText(text);
        fields.add(nameField);
        nameField.setBounds(12, 12, 12, 12);
        box.add(Box.createHorizontalStrut(6));
        box.add(nameField);
    }
}
