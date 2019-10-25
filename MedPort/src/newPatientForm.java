import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class newPatientForm extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField firstNameField, midNameField, lastNameField, DOBField, genderField, streetAddressField, cityField;
	private JTextField zipcodeField, ssnField, phoneNumberField;
	private JComboBox stateField;
	
	private JLabel lblNewLabel, lblMidName, lblLastName, lblDob;
	private JLabel lblGender, lblSsn, lblStreetAddr, lblState, lblCity, lblZipcode, lblPhone;
	
	
	private db_control dbc = new db_control();
	private JTextField aptNumField;
	
	public newPatientForm() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel formPanel = new JPanel();
		formPanel.setBackground(new Color(253, 245, 230));
		add(formPanel);
		formPanel.setLayout(null);
		
		firstNameField = new JTextField();
		firstNameField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		firstNameField.setBounds(88, 8, 130, 29);
		formPanel.add(firstNameField);
		firstNameField.setColumns(10);
		
		midNameField = new JTextField();
		midNameField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		midNameField.setBounds(305, 8, 121, 29);
		formPanel.add(midNameField);
		midNameField.setColumns(10);
		
		lastNameField = new JTextField();
		lastNameField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lastNameField.setBounds(513, 8, 130, 29);
		formPanel.add(lastNameField);
		lastNameField.setColumns(10);
		
		DOBField = new JTextField();
		DOBField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		DOBField.setBounds(89, 53, 105, 29);
		formPanel.add(DOBField);
		DOBField.setColumns(10);
		
		genderField = new JTextField();
		genderField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		genderField.setBounds(306, 53, 86, 29);
		formPanel.add(genderField);
		genderField.setColumns(10);
		
		streetAddressField = new JTextField();
		streetAddressField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		streetAddressField.setBounds(121, 126, 335, 29);
		formPanel.add(streetAddressField);
		streetAddressField.setColumns(10);
		
		cityField = new JTextField();
		cityField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cityField.setBounds(121, 166, 152, 29);
		formPanel.add(cityField);
		cityField.setColumns(10);
		
		stateField = new JComboBox();
		stateField.setModel(new DefaultComboBoxModel(new String[] {"", "AK", "AL", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"}));
		stateField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		stateField.setBounds(362, 166, 94, 29);
		formPanel.add(stateField);
		
		zipcodeField = new JTextField();
		zipcodeField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		zipcodeField.setBounds(549, 166, 94, 29);
		formPanel.add(zipcodeField);
		zipcodeField.setColumns(5);
		
		ssnField = new JTextField();
		ssnField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ssnField.setBounds(513, 52, 130, 29);
		formPanel.add(ssnField);
		ssnField.setColumns(10);
		
		phoneNumberField = new JTextField();
		phoneNumberField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		phoneNumberField.setBounds(121, 219, 152, 29);
		formPanel.add(phoneNumberField);
		phoneNumberField.setColumns(10);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		saveButton.setBounds(295, 278, 94, 37);
		formPanel.add(saveButton);
		
		lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(11, 8, 79, 29);
		formPanel.add(lblNewLabel);
		
		lblMidName = new JLabel("Mid Name");
		lblMidName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMidName.setBounds(226, 8, 79, 29);
		formPanel.add(lblMidName);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLastName.setBounds(433, 8, 79, 29);
		formPanel.add(lblLastName);
		
		lblDob = new JLabel("DOB");
		lblDob.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDob.setBounds(47, 53, 40, 29);
		formPanel.add(lblDob);
		
		lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblGender.setBounds(246, 52, 58, 29);
		formPanel.add(lblGender);
		
		lblSsn = new JLabel("SSN#");
		lblSsn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSsn.setBounds(469, 51, 48, 29);
		formPanel.add(lblSsn);
		
		lblStreetAddr = new JLabel("Street Address");
		lblStreetAddr.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblStreetAddr.setBounds(11, 126, 105, 29);
		formPanel.add(lblStreetAddr);
		
		lblState = new JLabel("State");
		lblState.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblState.setBounds(313, 166, 48, 29);
		formPanel.add(lblState);
		
		lblCity = new JLabel("City");
		lblCity.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCity.setBounds(76, 166, 40, 29);
		formPanel.add(lblCity);
		
		lblZipcode = new JLabel("Zipcode");
		lblZipcode.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblZipcode.setBounds(489, 166, 64, 29);
		formPanel.add(lblZipcode);
		
		lblPhone = new JLabel("Phone#");
		lblPhone.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPhone.setBounds(58, 219, 58, 29);
		formPanel.add(lblPhone);
		
		JLabel lblApt = new JLabel("Apt#");
		lblApt.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblApt.setBounds(489, 126, 48, 29);
		formPanel.add(lblApt);
		
		aptNumField = new JTextField();
		aptNumField.setText(" ");
		aptNumField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		aptNumField.setColumns(5);
		aptNumField.setBounds(549, 119, 94, 29);
		formPanel.add(aptNumField);
		
		
		
	}
}
