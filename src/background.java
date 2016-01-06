/*
 * ��  2016.1.6 ������  All Rights Reserved
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
	/*�÷��̾� 1 Ű �Է� ���� ����*/
	public static boolean p1_up = false;
	public static boolean p1_down = false;
	public static boolean p1_left = false;
	public static boolean p1_right = false;
	public static boolean p1_jump = false;
	
	/*�÷��̾� 2 Ű �Է� ���� ����*/
	public static boolean p2_up = false;
	public static boolean p2_down = false;
	public static boolean p2_left = false;
	public static boolean p2_right = false;
	public static boolean p2_jump = false;
	
	/*���� ���� & ���� ���� ����*/
	public static boolean gamemode = false;
	public static boolean gameend = true;
	
	/*�÷��̾� 1, 2 Ŭ���� ���� �� ��üȭ*/
	public static Player1 p1 = new Player1();
	public static Player2 p2 = new Player2();
	
	/*�豸�� Ŭ���� ���� �� ��üȭ*/
	Ball b = new Ball();
	
	static Mygame mg;	//���� �������� ȭ�� Ŭ���� ����
	static Mystart ms = new Mystart();	//���� �������� Ŭ���� ����
	
	/*�����, ȿ���� ������ ��θ� ���� ���� ����*/
	String s_Move = "�̵�.wav";
	String s_End = "����.wav";
	String s_Start = "����.wav";
	
	/*Ŭ�� Ŭ���� ����*/
	Clip clip;

	
	/*����, ���� Ŭ���� ���� �� ��üȭ*/
	helpkey h= new helpkey();
	Make_Info m= new Make_Info();
	
	/* ���α׷� ���� ȭ�� �ۼ�*/
	JMenuBar menuBar = new JMenuBar();
	JMenu mnGame = new JMenu("����");
	JMenuItem mnRestart = new JMenuItem("�ٽý���");
	JMenuItem mnEnd = new JMenuItem("����");

	JMenu mnInfo = new JMenu("����");
	JMenuItem mnKey = new JMenuItem("���۹��");
	JMenuItem mnMake = new JMenuItem("������");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//background Ŭ���� ��üȭ(�⺻ ������ ȣ��)
					background frame = new background();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}

	/* background Ŭ���� �⺻ ������ */
	public background() {
		super("���� �豸");
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

	/*���� ���� �Լ�*/
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
	
	/*���α׷� ȭ�鿡�� Ű ���۹ޱ�*/
	@Override
	public void keyPressed(KeyEvent e) {
		//���� ���� ȭ��
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
		/*���α׷� ���� ȭ��(���� ����&���� ����)*/
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

	/*�̻�� (���� adapter Ŭ���� ������� ���� �� ��)*/
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	/*�̵� ȿ���� ��� �Լ�*/
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
	/*���� ���� ȿ���� ��� �Լ�*/
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
	/*���� ���� ȿ���� ��� �Լ�*/
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

	/*Jmenubar �̺�Ʈ �߻���*/
	@Override
	public void actionPerformed(ActionEvent e) {
		//���޹��� MenuItem �ŰԺ����� '����'�� ���
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
		//���޹��� MenuItem �ŰԺ����� '�ٽ� ����'�� ���
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
		//���޹��� MenuItem �ŰԺ����� '���۹��'�� ���
		else if(e.getSource() == mnKey) {	//���� �޴� 
			h.setVisible(true);
		}
		//���޹��� MenuItem �ŰԺ����� '������'�� ���
		else if(e.getSource() == mnMake) {
			m.num = 1;
			m.setVisible(true);
		}
	}
}
