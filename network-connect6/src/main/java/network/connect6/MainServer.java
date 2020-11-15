package network.connect6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;

public class MainServer {
	ServerSocket server;
	
	Manager manager = new Manager();
	Random random = new Random();
	
	MainServer() {
		
	}
	
	void startServer() {
		try {
			server = new ServerSocket(7777);
			System.out.println("서버소켓이 생성되었습니다.");
			while(true) {
				Socket socket = server.accept();
				System.out.println("클라이언트와 연결되었습니다.");
				
				GameThread gt = new GameThread(socket);
				gt.start();
				
				manager.add(gt);
				
				System.out.println("접속자 수: " + manager.size());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(server != null) server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		MainServer server = new MainServer();
		server.startServer();
	}
	
	class GameThread extends Thread{
		Socket socket;
		BufferedReader reader;
	    PrintWriter writer;
		
		int roomNumber;
		String userName;
		ImageIcon image;
		
		boolean ready = false;
		
		Socket getSocket() {
			return socket;
		}
		
		int getRoomNumber() {
			return roomNumber;
		}
		
		String getUserName() {
			return userName;
		}
		
		ImageIcon getImageIcon() {
			return image;
		}
		
		boolean isReady() {
			return ready;
		}
		
		GameThread(Socket socket) {
			this.socket = socket;
		}
		
		public void run() { /* 미완성 */
			try {
				reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        writer=new PrintWriter(socket.getOutputStream(), true);
				
				String message;
				
				while((message = reader.readLine()) != null) {
					
					if(message.startsWith("[NAME]"))
			            userName = message.substring(6);
					
					if(message.startsWith("[IMAGE]"))
						image = new ImageIcon(message.substring(7));
					
					if(message.startsWith("[ROOM]")) {
						int room = Integer.parseInt(message.substring(6));
						
						if(!manager.isFull(room)) {
							roomNumber = room;
							writer.println(message);
							if(manager.isFull(roomNumber))
								writer.println("[READY]" + manager.getNamesInRoom(this, roomNumber));
							manager.sendToOthers(this, "[Enter]" + userName);
						}
						else {
							writer.println("[FULL]");
						}
							
					}
					
					if(message.startsWith("[START]")) {
						ready = true;
						writer.println("[CNAME]" + manager.getNamesInRoom(this, roomNumber));
						writer.println("[CIMAGE]" + manager.getImageInRoom(this, roomNumber));
						if(manager.isReady(roomNumber)) {
							int num = random.nextInt(2);
				              if(num == 0){
				                writer.println("[COLOR]BLACK");
				                manager.sendToOthers(this, "[COLOR]WHITE");
				              }
				              else{
				                writer.println("[COLOR]WHITE");
				                manager.sendToOthers(this, "[COLOR]BLACK");
				              }
						}
					}
					
					if(message.startsWith("[STONE]"))
						manager.sendToOthers(this, message);
					
					if(message.startsWith("[CHANGE]"))
						manager.sendToOthers(this, message);
					
					if(message.startsWith("[FINISH]"))
						manager.sendToOthers(this, message);
					
					if(message.startsWith("[RESTART]"))
						manager.sendToOthers(this, message);
					
					if(message.startsWith("[RESET]"))
						manager.sendToOthers(this, message);
					
					if(message.startsWith("[WAIT]")) {
						manager.sendToOthers(this, "[GOBACK]");
						roomNumber = 0;
						ready = false;
					}
					
					if(message.startsWith("GOBACK")) {
						roomNumber = 0;
						ready = false;
					}
					
					if(message.startsWith("[EXIT]")) {
						roomNumber = 0;
						manager.remove(this);
					}
					
					if(message.startsWith("[TIMER]"))
						manager.sendToRoom(roomNumber, message);
					
					if(message.startsWith("[REPAINT]"))
						manager.sendToRoom(roomNumber, message);
					
					if(message.startsWith("[BACK]")) {
						manager.sendToOthers(this, message + this.getName());
						roomNumber = 0;
						ready = false;
					}
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public class Manager extends Vector{

		Manager() {}

		void add(GameThread gt) {
			super.add(gt);
		}

		void remove(GameThread gt) {
			super.remove(gt);
		}
		
		GameThread getGT(int i) {
			return (GameThread)elementAt(i);
		}
		
		Socket getSocket(int i) {
			return getGT(i).getSocket();
		}

		void sendTo(int i, String message) {
			try {
				PrintWriter writer = new PrintWriter(getSocket(i).getOutputStream(), true);
				writer.println(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int getRoomNumber(int i) {
			return getGT(i).getRoomNumber();
		}
		
		synchronized boolean isFull(int roomNum) {
			int count = 0;
			for(int i = 0; i < size(); i++)
				if(roomNum == getRoomNumber(i)) count++;
			
			if(count >= 2) return true;
			return false;
		}
		
		void sendToRoom(int roomNum, String message) {
			for (int i = 0; i < size(); i++)
				if(roomNum == getRoomNumber(i))
					sendTo(i, message);
		}
		
		void sendToOthers(GameThread gt, String message) {
			for(int i = 0; i < size(); i++)
				if(getRoomNumber(i) == gt.getRoomNumber() && getGT(i) != gt)
					sendTo(i, message);
		}
		
		synchronized boolean isReady(int roomNum) {
			int count = 0;
			for (int i = 0; i < size(); i++)
				if(roomNum == getRoomNumber(i) && getGT(i).isReady())
					count++;
			if(count == 2) return true;
			return false;
		}

		String getNamesInRoom(GameThread gt, int roomNum) {
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < size(); i++)
				if(roomNum == getRoomNumber(i) && getGT(i) != gt)
					sb.append(getGT(i).getUserName());
			return sb.toString();
		}
		
		ImageIcon getImageInRoom(GameThread gt, int roomNum) {
			ImageIcon image = new ImageIcon();
			for(int i = 0; i < size(); i++)
				if(roomNum == getRoomNumber(i) && getGT(i) != gt)
					image = getGT(i).getImageIcon();
			return image;
		}

	}
}
