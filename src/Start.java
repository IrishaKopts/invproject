import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Start extends JFrame
{
	private static final long serialVersionUID = 2139255718245671889L;

	public Box mainBox;

	public JTextField intervalPlanningField;
	public JTextField numPlanningField;
	public JTextField numProductField;
	public JTextField numCapitalExpenditureField;
	public JTextField numberLongTermLoansField;
	public JTextField rateVATField;

	public Start()
	{
		super("Project");
		createMainBox();
		intervalPlanningField = createField("The interval of planning:");
		numPlanningField = createField("Number of intervals of planning:");
		numProductField = createField("The number of the species of products (services):");
		numCapitalExpenditureField = createField("Number of items of capital expenditures:");
		numberLongTermLoansField = createField("The number of attracted long-term loans:");
		rateVATField = createField("VAT rate:");
		createSaveBox();
		setContentPane(mainBox);
		pack();
		setResizable(false);
	}

	private JTextField createField(String label)
	{
		Box box = Box.createHorizontalBox();
		JLabel jLabel = new JLabel(label);
		jLabel.setPreferredSize(new Dimension(300, 15));
		box.add(jLabel);
		JTextField field = new JTextField(6);
		box.add(field);
		mainBox.add(Box.createVerticalStrut(12));
		mainBox.add(box);
		return field;
	}

	private void createMainBox()
	{
		mainBox = Box.createVerticalBox();
		mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
	}

	private void createSaveBox()
	{
		Box saveBox = Box.createHorizontalBox();
		JButton ok = new JButton("OK");
		ok.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					InvestmentProject investmentProject = new InvestmentProject();
					investmentProject.setRateVAT(Integer.parseInt(rateVATField.getText()));
					investmentProject.setNumberPlanning(Integer.parseInt(numPlanningField.getText()));
					investmentProject.setNumberProduct(Integer.parseInt(numProductField.getText()));
					investmentProject.setNumberLongTermLoansValue(Integer.parseInt(numberLongTermLoansField.getText()));
					investmentProject.setIntervalPlanningValue(Integer.parseInt(intervalPlanningField.getText()));
					investmentProject.setNumberCapitalExpenditureValue(Integer.parseInt(numCapitalExpenditureField.getText()));

					JFrame salesVolume = new Sales(investmentProject);
					salesVolume.setSize(new Dimension(1000, 500));
					salesVolume.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					salesVolume.setVisible(true);
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(rootPane, "Is invalid value");
				}

			}
		});
		saveBox.add(ok);

		mainBox.add(Box.createVerticalStrut(22));
		mainBox.add(saveBox);
	}

	public static void main(String[] args)
	{
		JFrame frame = new Start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
