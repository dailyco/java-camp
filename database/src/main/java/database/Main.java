package database;

import java.sql.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main extends JFrame implements ActionListener{
	User user = new User();

	MysqlConnection connect = new MysqlConnection();

	InitDisplay first = new InitDisplay();
	Join joinDisplay = new Join();
	Login loginDisplay = new Login();
	Menu menuDisplay = new Menu();
	MyInfo infoDisplay = new MyInfo();
	Edit editDisplay = new Edit();
	Search searchDisplay = new Search();
	
	JoinComplete joinFrame = new JoinComplete();
	LoginComplete loginFrame = new LoginComplete();
	ReallyCancel cancelFrame = new ReallyCancel();
	CancelComplete realCancelFrame = new CancelComplete();
	EditComplete editFrame = new EditComplete();

	Main() {
		setTitle("데이터베이스 관리");
		setSize(430, 640);
		setLocation(450, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		first.join.addActionListener(this);
		first.login.addActionListener(this);

		joinDisplay.ok.addActionListener(this);
		joinDisplay.no.addActionListener(this);
		joinDisplay.checkOverlap.addActionListener(this);

		loginDisplay.ok.addActionListener(this);
		loginDisplay.no.addActionListener(this);

		menuDisplay.info.addActionListener(this);
		menuDisplay.search.addActionListener(this);
		menuDisplay.edit.addActionListener(this);
		menuDisplay.cancel.addActionListener(this);
		menuDisplay.logout.addActionListener(this);
		menuDisplay.image.addActionListener(this);

		editDisplay.checkOverlap.addActionListener(this);
		editDisplay.ok.addActionListener(this);
		editDisplay.back.addActionListener(this);

		searchDisplay.id.addActionListener(this);
		searchDisplay.password.addActionListener(this);
		searchDisplay.nickname.addActionListener(this);
		searchDisplay.back.addActionListener(this);

		infoDisplay.edit.addActionListener(this);
		infoDisplay.back.addActionListener(this);

		joinFrame.yes.addActionListener(this);
		joinFrame.no.addActionListener(this);

		loginFrame.ok.addActionListener(this);

		cancelFrame.yes.addActionListener(this);
		cancelFrame.no.addActionListener(this);

		realCancelFrame.ok.addActionListener(this);

		editFrame.ok.addActionListener(this);

		add(first);

		setVisible(true);
	}

	public static void main(String args[]){
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();

		if(button == first.join) {
			remove(first);
			add(joinDisplay);
			revalidate();
			repaint();
		}

		if(button == first.login){
			remove(first);
			add(loginDisplay);
			revalidate();
			repaint();
		}

		if(button == joinDisplay.checkOverlap) {
			overlapCheck(joinDisplay.idText.getText());

		}

		if(button == joinDisplay.ok)  {
			checkEmpty();
		}

		if(button == joinFrame.yes) {
			joinFrame.dispose();
			remove(joinDisplay);
			add(loginDisplay);
			revalidate();
			repaint();
		}

		if(button == joinFrame.no) {
			joinFrame.dispose();
			remove(joinDisplay);
			add(first);
			revalidate();
			repaint();
		}

		if(button == joinDisplay.no) {
			joinDisplayInit();
			remove(joinDisplay);
			add(first);
			revalidate();
			repaint();
		}

		if(button == loginDisplay.ok) {
			loginCheck();
			loginDisplayInit();
		}

		if(button == loginFrame.ok) {
			loginFrame.dispose();
			remove(loginDisplay);
			add(menuDisplay);
			revalidate();
			repaint();
		}

		if(button == loginDisplay.no) {
			loginDisplayInit();
			remove(loginDisplay);
			add(first);
			revalidate();
			repaint();
		}

		if(button == menuDisplay.image) {
			imageSetting();
			changeGender();
		}

		if(button == menuDisplay.info) {
			infoDisplay.set(user);
			remove(menuDisplay);
			add(infoDisplay);
			revalidate();
			repaint();
		}

		if(button == infoDisplay.edit) {
			remove(infoDisplay);
			add(editDisplay);
			revalidate();
			repaint();
			new EditNotice();
		}

		if(button == editDisplay.ok) {
			checkEmptyAtEdit();
		}

		if(button == editDisplay.back) {
			remove(editDisplay);
			add(menuDisplay);
			revalidate();
			repaint();
		}

		if(button == infoDisplay.back) {
			remove(infoDisplay);
			add(menuDisplay);
			revalidate();
			repaint();
		}

		if(button == menuDisplay.search) {
			remove(menuDisplay);
			add(searchDisplay);
			revalidate();
			repaint();
			new SearchNotice();
		}

		if(button == searchDisplay.id) {
			new SearchID(user.id);
		}

		if(button == searchDisplay.password) {
			new SearchPassword(user.password);
		}

		if(button == searchDisplay.nickname) {
			new SearchNickname(user.nickname);
		}

		if(button == searchDisplay.back) {
			remove(searchDisplay);
			add(menuDisplay);
			revalidate();
			repaint();
		}

		if(button == menuDisplay.edit) {
			remove(menuDisplay);
			add(editDisplay);
			revalidate();
			repaint();
			new EditNotice();
		}

		if(button == editDisplay.checkOverlap) {
			overlapCheckAtEdit(editDisplay.idText.getText());
		}

		if(button == editFrame.ok) {
			editFrame.dispose();
			remove(editDisplay);
			add(menuDisplay);
			revalidate();
			repaint();
		}

		if(button == editDisplay.back) {
			remove(editDisplay);
			add(menuDisplay);
			revalidate();
			repaint();
		}

		if(button == menuDisplay.cancel) {
			cancelFrame.setVisible(true);
		}

		if(button == cancelFrame.yes) {
			cancelFrame.dispose();
			cancelInfo();
			realCancelFrame.setVisible(true);
		}

		if(button == realCancelFrame.ok) {
			realCancelFrame.dispose();
			remove(menuDisplay);
			add(first);
			revalidate();
			repaint();
		}

		if(button == cancelFrame.no) {
			cancelFrame.dispose();
			new WithdrawCancel();
		}

		if(button == menuDisplay.logout) {
			remove(menuDisplay);
			add(first);
			revalidate();
			repaint();
		}

	}

	private void overlapCheck(String id) {

		if(id.equals("")) {
			joinDisplay.check = false;
			new EmptyID();
			return;
		}

		try {

			connect.stmt = (Statement)connect.conn.createStatement();
			connect.rs = connect.stmt.executeQuery("select * from user_manager where id = '" + id +"'");
			String getID = "";

			while(connect.rs.next()) {
				getID = connect.rs.getString("id");
			}

			if(getID.equals("")) {
				new NoIdOverlap();
				joinDisplay.check = true;
			}

			else {
				new IdOverlap();
				joinDisplay.check = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void overlapCheckAtEdit(String id) {

		if(editDisplay.idCheck == true) {

			if(id.equals("")) {
				editDisplay.check = false;
				new EmptyID();
				return;
			}

			try {
				connect.stmt = (Statement)connect.conn.createStatement();
				connect.rs = connect.stmt.executeQuery("select * from user_manager where id = '" + id +"'");
				String getID = "";

				while(connect.rs.next()) {
					getID = connect.rs.getString("id");
				}

				if(getID.equals("")) {
					new NoIdOverlap();
					editDisplay.check = true;
				}
				else {
					new IdOverlap();
					editDisplay.check = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else {
			new SelectID();
		}

	}

	private void checkEmptyAtEdit() {

		if(editDisplay.idCheck == true && editDisplay.idText.getText().equals("") || editDisplay.nnCheck == true && editDisplay.nnText.getText().equals("") || editDisplay.pwCheck == true && editDisplay.pwText.getText().equals("")) {
			new Empty();
		}
		else if(editDisplay.idCheck == true && editDisplay.check == false) {
			new OverlapCheck();
		}
		else {
			try {
				connect.stmt = (Statement)connect.conn.createStatement();

				if(editDisplay.idCheck == true) {
					connect.stmt.executeUpdate("update user_manager set id = '" + editDisplay.idText.getText() + "' where id = '" + user.id + "'");
					user.setID(editDisplay.idText.getText());
				}

				if(editDisplay.pwCheck == true) {
					connect.stmt.executeUpdate("update user_manager set password = '" + editDisplay.pwText.getText() + "' where id = '" + user.id + "'");
					user.setPassword(editDisplay.pwText.getText());
				}

				if(editDisplay.nnCheck == true) {
					connect.stmt.executeUpdate("update user_manager set name = '" + editDisplay.nnText.getText() + "' where id = '" + user.id + "'");
					user.setNickname(editDisplay.nnText.getText());
					menuDisplay.setLabel(editDisplay.nnText.getText());
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			editDisplayInit();
			editFrame.setVisible(true);
		}

	}

	private void checkEmpty() {

		if(joinDisplay.idText.getText().equals("") || joinDisplay.nnText.getText().equals("") || joinDisplay.pwText.getText().equals("")) {
			new Empty();
		}
		else if(joinDisplay.check == false) {
			new OverlapCheck();
		}
		else if(joinDisplay.pwText.getText().length() < 4) {
			new PasswordError();
		}
		else {
			user.set(joinDisplay.nnText.getText(), joinDisplay.idText.getText(), joinDisplay.pwText.getText(), "F");
			addUser(user);
			joinDisplayInit();
			joinFrame.setVisible(true);
		}

	}

	private void addUser(User user) {
		try {
			connect.stmt = (Statement)connect.conn.createStatement();
			connect.stmt.executeUpdate("insert into user_manager "+ "(name,id,password) value ('" + user.nickname + "','" + user.id + "','" + user.password + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loginCheck() {
		String id = loginDisplay.idText.getText();
		String pw = loginDisplay.pwText.getText();

		if(id.equals("") || pw.equals("")) {
			new Empty();
		}
		else {
			try {
				connect.stmt = (Statement)connect.conn.createStatement();
				connect.rs = connect.stmt.executeQuery("select * from user_manager where id = '" + id +"'");
				String getID = "";
				String getPW = "";
				String getNN = "";
				String gender = "";

				while(connect.rs.next()) {
					getNN = connect.rs.getString("name");
					getID = connect.rs.getString("id");
					getPW = connect.rs.getString("password");
					gender = connect.rs.getString("gender");
				}

				if(getID.equals("")) {
					new NoExistID();
				}
				else if(!getPW.equals(pw)) {
					new NoExistPW();
				}
				else {
					user.set(getNN, getID, getPW, gender);
					loginSetting();
					loginFrame.setVisible(true);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void loginSetting() {
		menuDisplay.setLabel(user.nickname);
		menuDisplay.setImage(user.gender);
		infoDisplay.set(user);
	}

	private void cancelInfo() {

		try {
			connect.stmt = (Statement)connect.conn.createStatement();
			connect.stmt.executeUpdate("delete from user_manager where id like '" + user.id + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		user.set("", "", "", "");
	}

	private void joinDisplayInit() {
		joinDisplay.idText.setText("");
		joinDisplay.pwText.setText("");
		joinDisplay.nnText.setText("");
		joinDisplay.check = false;
	}

	private void loginDisplayInit() {
		loginDisplay.idText.setText("");
		loginDisplay.pwText.setText("");
	}

	private void editDisplayInit() {
		editDisplay.idText.setText("");
		editDisplay.pwText.setText("");
		editDisplay.nnText.setText("");
		editDisplay.idCheck = false;
		editDisplay.pwCheck = false;
		editDisplay.nnCheck = false;
		editDisplay.check = false;

		editDisplay.id.setOpaque(false);
		editDisplay.password.setOpaque(false);
		editDisplay.nickname.setOpaque(false);
	}

	private void imageSetting() {
		
		if(user.gender.equals("F")) {
			user.setGender("M");
			menuDisplay.setImage("M");
			menuDisplay.repaint();
		}
		else {
			user.setGender("F");
			menuDisplay.setImage("F");
			menuDisplay.repaint();
		}
		
	}

	private void changeGender() {
		
		try {
			connect.stmt = (Statement)connect.conn.createStatement();
			connect.stmt.executeUpdate("update user_manager set gender = '" + user.gender + "' where id = '" + user.id + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
