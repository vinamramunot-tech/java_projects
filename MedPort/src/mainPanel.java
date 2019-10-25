import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class mainPanel extends JPanel{
	
	private JTextField nameSearch, idSearch, ssnSearch;
	private JButton searchButton,logoutButton;

	private JScrollPane scrollPane;
	private JList list;
	
	private JPanel profilePanel, profileInputPanel, bottomPanel, resultPanel, profileSection;
	private JLabel userLabel, SSNLabel, genderLabel, DOBLabel, patientIDLabel, firstNameLabel, midNameLabel, lastNameLabel, primaryDoctorLabel;
	private JLabel streetLabel, cityStateLabel;

	private db_control dbc = new db_control();
	private JLabel aptLabel;

	public mainPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout(0, 5));
		JPanel searchPanel = new JPanel();
		add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new GridLayout(0, 1, 0, 3));
		
		JPanel inputPanel = new JPanel();
		searchPanel.add(inputPanel);
		inputPanel.setLayout(new GridLayout(2, 7, 5, 5));
		
		JPanel blank0 = new JPanel();
		inputPanel.add(blank0);
		
		JLabel lblname = new JLabel("Enter Name");
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		inputPanel.add(lblname);
		
		JLabel lblid = new JLabel("Enter ID");
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		inputPanel.add(lblid);
		
		JLabel lblssn = new JLabel("Enter 4 digit SSN:");
		lblssn.setHorizontalAlignment(SwingConstants.CENTER);
		lblssn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		inputPanel.add(lblssn);
		
		JPanel blank1 = new JPanel();
		inputPanel.add(blank1);
		
		JPanel blank2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) blank2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		inputPanel.add(blank2);
		
		userLabel = new JLabel("Login as: " + userProfile.getUser());
		userLabel.setForeground(Color.BLUE);
		userLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		blank2.add(userLabel);
		
		JPanel blank3 = new JPanel();
		inputPanel.add(blank3);
		
		nameSearch = new JTextField();
		nameSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					searchPatient();
				}
			}
		});
		nameSearch.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		nameSearch.setHorizontalAlignment(SwingConstants.LEFT);
		inputPanel.add(nameSearch);
		nameSearch.setColumns(10);
		
		idSearch = new JTextField();
		idSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					searchPatient();
				}
			}
		});
		idSearch.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		idSearch.setHorizontalAlignment(SwingConstants.LEFT);
		inputPanel.add(idSearch);
		idSearch.setColumns(10);
		
		ssnSearch = new JTextField();
		ssnSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					searchPatient();
				}
			}
		});
		ssnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ssnSearch.setHorizontalAlignment(SwingConstants.LEFT);
		inputPanel.add(ssnSearch);
		ssnSearch.setColumns(10);
		
		searchButton = new JButton("Search");
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchPatient();
			}
		});
		
		searchButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					searchPatient();
				}
			}
		});
		searchButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		inputPanel.add(searchButton);
		
		JPanel blank4 = new JPanel();
		inputPanel.add(blank4);
		inputPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{nameSearch, idSearch, ssnSearch, searchButton}));
		
		resultPanel = new JPanel();
		searchPanel.add(resultPanel);
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(450, 50));
		scrollPane.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setMinimumSize(new Dimension(450, 50));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		resultPanel.add(scrollPane);
		
		list = new JList();
		list.setMinimumSize(new Dimension(200, 20));
		list.setSize(new Dimension(200, 20));
		//////////////////////////////////////////////////////////////////////ADD SEARCH RESULT LIST HERE///////////////////////////////////
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {" search result show here"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		list.setVisibleRowCount(2);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		bottomPanel = new JPanel();
		FlowLayout fl_bottomPanel = (FlowLayout) bottomPanel.getLayout();
		fl_bottomPanel.setAlignment(FlowLayout.RIGHT);
		add(bottomPanel, BorderLayout.SOUTH);
		
		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		logoutButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					System.exit(0);
				}
			}
		});
		logoutButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		bottomPanel.add(logoutButton);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.LIGHT_GRAY);
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(5, 5));
		
		profilePanel = new JPanel();
		profilePanel.setBackground(Color.LIGHT_GRAY);
		centerPanel.add(profilePanel, BorderLayout.NORTH);
		profilePanel.setLayout(new GridLayout(0, 2, 5, 5));
		
		profileSection = new JPanel();
		profileSection.setBackground(Color.WHITE);
		profileSection.setPreferredSize(new Dimension(400, 200));
		profileSection.setMinimumSize(new Dimension(400, 200));
		profileSection.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		profilePanel.add(profileSection);
		profileSection.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Patient ID: ");
		lblNewLabel.setBounds(11, 0, 89, 38);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(lblNewLabel);
		
		patientIDLabel = new JLabel("-");
		patientIDLabel.setBounds(101, 0, 127, 38);
		patientIDLabel.setHorizontalAlignment(SwingConstants.LEFT);
		patientIDLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(patientIDLabel);
		
		JLabel lblDOB = new JLabel("DOB: ");
		lblDOB.setBounds(11, 87, 64, 38);
		lblDOB.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(lblDOB);
		
		DOBLabel = new JLabel("-");
		DOBLabel.setBounds(79, 87, 105, 38);
		DOBLabel.setHorizontalAlignment(SwingConstants.LEFT);
		DOBLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(DOBLabel);
		
		JLabel lblGender = new JLabel("Gender: ");
		lblGender.setBounds(200, 87, 59, 38);
		lblGender.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(lblGender);
		
		genderLabel = new JLabel("-");
		genderLabel.setBounds(259, 87, 64, 38);
		genderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		genderLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(genderLabel);
		
		JLabel lblSSN = new JLabel("SSN: ");
		lblSSN.setBounds(342, 87, 42, 38);
		lblSSN.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(lblSSN);
		
		SSNLabel = new JLabel("XXX-XX-XXXX");
		SSNLabel.setBounds(388, 87, 127, 38);
		SSNLabel.setHorizontalAlignment(SwingConstants.LEFT);
		SSNLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(SSNLabel);
		
		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setBounds(11, 121, 76, 38);
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(lblAddress);
		
		streetLabel = new JLabel("---  ----- ------");
		streetLabel.setBounds(89, 121, 201, 38);
		streetLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(streetLabel);
		
		Label lblPrimaryDoctor = new Label("Primary Doctor:");
		lblPrimaryDoctor.setBounds(342, 121, 139, 38);
		lblPrimaryDoctor.setFont(new Font("Times New Roman", Font.BOLD, 15));
		profileSection.add(lblPrimaryDoctor);
		
		primaryDoctorLabel = new JLabel("------------");
		primaryDoctorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		primaryDoctorLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		primaryDoctorLabel.setBounds(335, 149, 145, 38);
		primaryDoctorLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(primaryDoctorLabel);
		
		cityStateLabel = new JLabel("---- ----- ---- -----");
		cityStateLabel.setBounds(89, 149, 234, 38);
		cityStateLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		profileSection.add(cityStateLabel);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblName.setBounds(11, 48, 59, 38);
		profileSection.add(lblName);
		
		firstNameLabel = new JLabel("-");
		firstNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		firstNameLabel.setBounds(79, 48, 89, 38);
		profileSection.add(firstNameLabel);
		
		midNameLabel = new JLabel("-");
		midNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		midNameLabel.setBounds(176, 49, 89, 38);
		profileSection.add(midNameLabel);
		
		lastNameLabel = new JLabel("-");
		lastNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lastNameLabel.setBounds(273, 49, 117, 38);
		profileSection.add(lastNameLabel);
		
		aptLabel = new JLabel("Apt# ");
		aptLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		aptLabel.setBounds(11, 149, 76, 38);
		profileSection.add(aptLabel);
		
		JPanel blankProfilePanel = new JPanel();
		profilePanel.add(blankProfilePanel);
		
		profileInputPanel = new JPanel();
		profileInputPanel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		centerPanel.add(profileInputPanel, BorderLayout.CENTER);
		
		JButton addNewButton = new JButton("Add New Patient");
		addNewButton.setMaximumSize(new Dimension(50, 20));
		addNewButton.setMinimumSize(new Dimension(50, 20));
		addNewButton.setBackground(Color.WHITE);
		addNewButton.setPreferredSize(new Dimension(50, 20));
		addNewButton.setSize(new Dimension(50, 20));
		addNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressAddNewPatient();
			}
		});
		profileInputPanel.setLayout(new BorderLayout(0, 0));
		addNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		profileInputPanel.add(addNewButton);
		

	}//end panel
	void pressAddNewPatient() {
		profileInputPanel.removeAll();
		
		newPatientForm npf = new newPatientForm();
		JScrollPane scrPane = new JScrollPane(npf);
		scrPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		profileInputPanel.add(npf);
		npf.setVisible(true);
		profileInputPanel.repaint();
		profileInputPanel.validate();
		
	}
	
	void searchPatient(){

		dbc.getPatientProfile(idSearch.getText(), nameSearch.getText(), ssnSearch.getText());
		
		if(patientProfile.patientID != "") {
			//patient info
			patientIDLabel.setForeground(Color.BLACK);
	    	patientIDLabel.setText(patientProfile.patientID);
	    	firstNameLabel.setText(patientProfile.firstName);
	    	midNameLabel.setText(patientProfile.midName);
	    	lastNameLabel.setText(patientProfile.lastName);
	    	DOBLabel.setText(patientProfile.dateOfBirth);
	    	genderLabel.setText(patientProfile.gender);
	    	primaryDoctorLabel.setText(patientProfile.PrimaryDoctor);
	    	SSNLabel.setText("XXX-XX-"+ patientProfile.ssnSerial);
	    	
	    	//address
	    	String address = patientProfile.streetNum + " "+ patientProfile.streetName ;
	    	String cityStateZip = patientProfile.cityName + ", "+ patientProfile.stateName + " " + patientProfile.zipcode ;
	    	streetLabel.setText(address);
	    	aptLabel.setText("Apt#" + patientProfile.aptNum);
	    	cityStateLabel.setText(cityStateZip);
		}
		else {
			
			patientIDLabel.setForeground(Color.RED);
			patientIDLabel.setText("Search Not Found!");
	    	firstNameLabel.setText("");
	    	midNameLabel.setText("");
	    	lastNameLabel.setText("");
	    	DOBLabel.setText("");
	    	genderLabel.setText("");
	    	primaryDoctorLabel.setText("");
		}
		
	}//end search
}
