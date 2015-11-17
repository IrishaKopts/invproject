import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Sales extends JFrame
{
    private static final long serialVersionUID = 8630455390396412928L;
    private Box mainBox;
    private List<List<JTextField>> sales = new ArrayList();
    private List<List<JTextField>> products = new ArrayList();
    private Integer numberPlanning;
    private Integer numberProduct;
    private InvestmentProject investmentProject;

    public Sales(final InvestmentProject investmentProject)
    {
        super("Products");
        this.investmentProject = investmentProject;
        this.numberPlanning = investmentProject.getNumberPlanning();
        this.numberProduct = investmentProject.getNumberProduct();
        mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        createSales();
        createProducts();
        createSaveBox();
        setContentPane(mainBox);
    }

    private void createSales()
    {
        JLabel salesLabel = new JLabel("The volume of sales");
        mainBox.add(salesLabel);
        createTopBox("Service", "Unit of production");

        for(int i = 0; i < numberProduct; i++)
        {
            List<JTextField> list = createRowField();
            products.add(list);
        }
    }

    private void createProducts()
    {
        JLabel productsLabel = new JLabel("Sales of products (works, services) in natural terms");
        mainBox.add(productsLabel);
        createTopBox("Sales price of product", "Price per unit");

        for(int i = 0; i < numberProduct; i++)
        {
            List<JTextField> list = createRowField();
            sales.add(list);
        }
    }

    private List createRowField()
    {
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalStrut(6));
        List<JTextField> list = new ArrayList<JTextField>();

        for(int j = 0; j < numberPlanning + 2; j++)
        {
            JTextField field = createTextField(box, "", true);
            list.add(field);
        }
        mainBox.add(box);
        return list;
    }

    private void createTopBox(String firstLabel, String secondLabel)
    {
        Box topBox = Box.createHorizontalBox();

        createTextField(topBox, firstLabel, false);
        createTextField(topBox, secondLabel, false);

        for(int index = 0; index < numberPlanning; index++)
        {
            Integer numQuarter = index + 1;
            String text = numQuarter.toString() + " quarter";
            createTextField(topBox, text, false);
        }
        mainBox.add(Box.createHorizontalStrut(3));
        mainBox.add(topBox);
    }

    private void createSaveBox()
    {
        Box saveBox = Box.createHorizontalBox();
        JButton ok = new JButton("OK");
        ok.addActionListener(new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    JFrame revenueSale = new Revenue(sales, products, investmentProject);
                    revenueSale.setSize(new Dimension(1000, 500));
                    revenueSale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    revenueSale.setVisible(true);
                } catch (Exception exc)
                {
                    JOptionPane.showMessageDialog(rootPane, "Is invalid value");
                }

            }
        });
        saveBox.add(ok);
        mainBox.add(Box.createVerticalStrut(22));
        mainBox.add(saveBox);
    }

    private JTextField createTextField(Box box, String text, boolean isEnabled)
    {
        JTextField nameField = new JTextField(3);
        nameField.setEnabled(isEnabled);
        nameField.setText(text);
        nameField.setBounds(12, 12, 12, 12);
        box.add(Box.createHorizontalStrut(6));
        box.add(nameField);
        return nameField;
    }
}