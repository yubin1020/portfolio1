/*
 * ⓒ  2016.1.6 조유빈  All Rights Reserved
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
*/

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.plaf.SliderUI;

public class background extends JFrame implements KeyListener, ActionListener {
	/*플레이어 1 키 입력 변수 선언*/
	public static boolean p1_up = false;
	public static boolean p1_down = false;
	public static boolean p1_left = false;
	public static boolean p1_right = false;
	public static boolean p1_jump = false;
	
	/*플레이어 2 키 입력 변수 선언*/
	public static boolean p2_up = false;
	public static boolean p2_down = false;
	public static boolean p2_left = false;
	public static boolean p2_right = false;
	public static boolean p2_jump = false;
	
	/*게임 시작 & 종료 변수 선언*/
	public static boolean gamemode = false;
	public static boolean gameend = true;
	
	/*플레이어 1, 2 클래스 선언 및 객체화*/
	public static Player1 p1 = new Player1();
	public static Player2 p2 = new Player2();
	
	/*배구공 클래스 선언 및 객체화*/
	Ball b = new Ball();
	
	static Mygame mg;	//실제 게임진행 화면 클래스 선언
	static Mystart ms = new Mystart();	//실제 게임진행 클래스 선언
	
	/*배경음, 효과음 파일의 경로를 담은 변수 선언*/
	String s_Move = "이동.wav";
	String s_End = "종료.wav";
	String s_Start = "시작.wav";
	
	/*클립 클래스 선언*/
	Clip clip;

	
	/*헬프, 정보 클래스 선언 및 객체화*/
	helpkey h= new helpkey();
	Make_Info m= new Make_Info();
	
	/* 프로그램 메인 화면 작성*/
	JMenuBar menuBar = new JMenuBar();
	JMenu mnGame = new JMenu("게임");
	JMenuItem mnRestart = new JMenuItem("다시시작");
	JMenuItem mnEnd = new JMenuItem("종료");

	JMenu mnInfo = new JMenu("정보");
	JMenuItem mnKey = new JMenuItem("조작방법");
	JMenuItem mnMake = new JMenuItem("만든이");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//background 클래스 객체화(기본 생성자 호출)
					background frame = new background();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}

	/* background 클래스 기본 생성자 */
	public background() {
		super("어흥 배구");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 933, 700);
		setBounds(100, 100, 933, 725);
		this.addKeyListener(this);

		setJMenuBar(menuBar);

		menuBar.add(mnGame);
		mnGame.add(mnRestart);
		mnGame.add(mnEnd);

		mnRestart.setEnabled(false);

		menuBar.add(mnInfo);
		mnInfo.add(mnKey);
		mnInfo.add(mnMake);

		this.add(ms);

		mnKey.addActionListener(this);
		mnMake.addActionListener(this);
		mnRestart.addActionListener(this);
		mnEnd.addActionListener(this);

		this.setResizable(false);
	}

	/*게임 시작 함수*/
	void GameStart() {
		mg = new Mygame();
		p1 = new Player1();
		p2 = new Player2();
		b = new Ball();
		ms.setVisible(false);
		this.add(mg);
		this.remove(ms);
		mnRestart.setEnabled(true);
		gameend = false;

		// this.remove(mg);
		/*
		 * mg.GameState = false; mg.setBGM(); mg = new Mygame(); this.add(mg);
		 */

		b.start();
		p1.start();
		p2.start();
	}
	
	/*프로그램 화면에서 키 조작받기*/
	@Override
	public void keyPressed(KeyEvent e) {
		//실제 게임 화면
		if (gamemode == true) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				p1_left = true;
				break;
			case KeyEvent.VK_D:
				p1_right = true;
				break;
			case KeyEvent.VK_W:
				mg.p1_jump = true;
				p1_up = true;
				break;
			case KeyEvent.VK_S:
				p1_down = true;
				break;
			case KeyEvent.VK_G:
				mg.p1_event = 1;
				break;
			case KeyEvent.VK_H:
				mg.p1_event= 2;
				break;
			case KeyEvent.VK_UP:
				mg.p2_jump = true;
				p2_up = true;
				break;
			case KeyEvent.VK_DOWN:
				p2_down = true;
				break;
			case KeyEvent.VK_LEFT:
				p2_left = true;
				break;
			case KeyEvent.VK_RIGHT:
				p2_right = true;
				break;
			case KeyEvent.VK_DELETE:
				mg.p2_event = 1;
				break;
			case KeyEvent.VK_END:
				mg.p2_event = 2;
				break;
			}
		} 
		/*프로그램 메인 화면(게임 시작&게임 종료)*/
		if (gameend == true){
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				ms.end = false;
				ms.start = true;
				setMove();
				ms.repaint();
				break;
			case KeyEvent.VK_DOWN:
				ms.end = true;
				ms.start = false;
				setMove();
				ms.repaint();
				break;
			case KeyEvent.VK_ENTER:
				if (ms.end == true) {
					setEnd();
					try {
						Thread.sleep(1800);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.exit(EXIT_ON_CLOSE);
				} else {
					setStart();
					try {
						Thread.sleep(1800);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					gamemode = true;
					GameStart();
				}
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (gamemode == true) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				p1_up = false;
				break;
			case KeyEvent.VK_S:
				p1_down = false;
				break;
			case KeyEvent.VK_A:
				p1_left = false;
				break;
			case KeyEvent.VK_D:
				p1_right = false;
				break;
				
			case KeyEvent.VK_G:
				mg.p1_event = 0;
				break;
			case KeyEvent.VK_H:
				mg.p1_event= 0;
				break;

			case KeyEvent.VK_UP:
				p2_up = false;
				break;
			case KeyEvent.VK_DOWN:
				p2_down = false;
				break;
			case KeyEvent.VK_LEFT:
				p2_left = false;
				break;
			case KeyEvent.VK_RIGHT:
				p2_right = false;
				break;
			case KeyEvent.VK_DELETE:
				mg.p2_event = 0;
				break;
			case KeyEvent.VK_END:
				mg.p2_event = 0;
				break;
			}
		}
	}

	/*미사용 (추후 adapter 클래스 사용으로 제거 할 것)*/
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	/*이동 효과음 출력 함수*/
	public void setMove() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(
					s_Move));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*게임 종료 효과음 출력 함수*/
	public void setEnd() {
		try {
			if (gamemode == true) {
				gamemode = false;
				mg.GameState = false;
				mg.setBGM();
			}
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(
					s_End));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*게임 시작 효과음 출력 함수*/
	public void setStart() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(
					s_Start));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*Jmenubar 이벤트 발생시*/
	@Override
	public void actionPerformed(ActionEvent e) {
		//전달받은 MenuItem 매게변수가 '종료'일 경우
		if (e.getSource() == mnEnd) {	
			setEnd();
			try {
				Thread.sleep(1800);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(EXIT_ON_CLOSE);
		}
		//전달받은 MenuItem 매게변수가 '다시 시작'일 경우
		else if (e.getSource() == mnRestart) {
			mg.setVisible(false);
			ms.setVisible(true);
			this.add(ms);
			gamemode = false;
			gameend = true;
			
			background.mg.GameState = false;
			mg.setBGM();
			mnRestart.setEnabled(false);
			
			this.remove(mnEnd);
		}
		//전달받은 MenuItem 매게변수가 '조작방법'일 경우
		else if(e.getSource() == mnKey) {	//헬프 메뉴 
			h.setVisible(true);
		}
		//전달받은 MenuItem 매게변수가 '만든이'일 경우
		else if(e.getSource() == mnMake) {
			m.num = 1;
			m.setVisible(true);
		}
	}
}
