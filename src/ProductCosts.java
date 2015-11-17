import javax.swing.*;
import java.util.*;

public class ProductCosts extends JFrame
{
    private Box mainBox;
    private List<JTextField> costs  = new ArrayList<JTextField>();
    private static final long serialVersionUID = 8630455390396412928L;

    public ProductCosts(final InvestmentProject investmentProject)
    {
        super("Product costs");
        //createProductCost(investmentProject);
        mainBox = Box.createVerticalBox();
        setContentPane(mainBox);
    }

    private void createProductCost(InvestmentProject investmentProject)
    {
        Box topBox = Box.createHorizontalBox();
        createTextField(topBox, "Name cost", false, costs);
        createTextField(topBox, "rub with VAT per unit of production", false, costs);

        for(int index = 0; index < investmentProject.getNumberPlanning(); index++)
        {
            Integer numQuarter = index + 1;
            String text= numQuarter.toString() + " quarter";
            createTextField(topBox, text, false, costs);
        }
        createTextField(topBox, "Total", false, costs);
        mainBox.add(Box.createHorizontalStrut(3));
        mainBox.add(topBox);
    }

    private void createTextField(Box box, String text, boolean isEnabled, java.util.List<JTextField> fields)
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