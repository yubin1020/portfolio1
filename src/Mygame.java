import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.Closeable;
import java.io.File;
import java.sql.Connection;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.JPanel;

class Mygame extends JPanel implements LineListener { // 선그리기
	String fileName = "BGM.wav";
	Clip clip;
	int p1_score = 0;
	int p2_score = 0;
	int speed_x = 0;
	int speed_y = 5;

	Image back1 = Toolkit.getDefaultToolkit().getImage("배경.png");
	Image back2 = Toolkit.getDefaultToolkit().getImage("구름.gif");
	Image back3 = Toolkit.getDefaultToolkit().getImage("햇님.png");
	Image back4 = Toolkit.getDefaultToolkit().getImage("네트.png");

	Image ball = Toolkit.getDefaultToolkit().getImage("회전공2.gif");

	Image player1 = Toolkit.getDefaultToolkit().getImage("좌측.gif");
	Image player1_jump = Toolkit.getDefaultToolkit().getImage("좌점.gif");

	Image player2 = Toolkit.getDefaultToolkit().getImage("우측.gif");
	Image player2_jump = Toolkit.getDefaultToolkit().getImage("우점.gif");

	Image player_leftslide = Toolkit.getDefaultToolkit().getImage("좌슬.gif");
	Image player_rightslide = Toolkit.getDefaultToolkit().getImage("우슬.gif");

	Image Score0 = Toolkit.getDefaultToolkit().getImage("0.gif");
	Image Score1 = Toolkit.getDefaultToolkit().getImage("1.gif");
	Image Score2 = Toolkit.getDefaultToolkit().getImage("2.gif");
	Image Score3 = Toolkit.getDefaultToolkit().getImage("3.gif");
	Image Score4 = Toolkit.getDefaultToolkit().getImage("4.gif");
	Image Score5 = Toolkit.getDefaultToolkit().getImage("5.gif");

	Image win = Toolkit.getDefaultToolkit().getImage("win.gif");
	Image lose = Toolkit.getDefaultToolkit().getImage("lose.gif");

	int p1_motion = 0; // 0 그냥, 1 점프, 2 좌슬, 3우슬
	int p2_motion = 0; // 0 그냥, 1 점프, 2 좌슬, 3우슬
	boolean ball_touch = false;
	boolean reset = false;

	int p1_x = 0; // 1P 캐릭터 그림그리는 x좌표
	int p1_y = 533; // 1P 캐릭터 그림그리는 y좌표

	int p2_x = 849; // 2P 캐릭터 그림그리는 x좌표
	int p2_y = 533; // 2P 캐릭터 그림그리는 y좌표

	int ball_x = 10; // 공의 x좌표 1p 머리 (10, 1) (860,1)
	int ball_y = 1; // 공의 y좌표 10, 1

	boolean p1_jump = false;
	boolean p2_jump = false;

	public static int p1_event = 0;
	public static int p2_event = 0;

	boolean check = false;
	boolean GameState = true;

	protected void finalize() throws Throwable {
		//System.out.println("OUT!!!");
	}

	public Mygame() {
		setBGM();
	}

	public void getScore(int player) {
		reset = true;

		p1_x = 0; // 1P 캐릭터 그림그리는 x좌표
		p1_y = 533; // 1P 캐릭터 그림그리는 y좌표

		p2_x = 849; // 2P 캐릭터 그림그리는 x좌표
		p2_y = 533; // 2P 캐릭터 그림그리는 y좌표

		if (player == 1) {
			p1_score++;
			ball_x = 10; // 공의 x좌표 1p 머리 (10, 1) (860,1)
			ball_y = 1; //
		} else {
			p2_score++;
			ball_x = 860; // 공의 x좌표 1p 머리 (10, 1) (860,1)
			ball_y = 1; //
		}

		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		Back(g);
		Ball(g);
		P1(g);
		P2(g);
		Score(g);
	}

	public void Score(Graphics g) {
		switch (p1_score) {
		case 0:
			g.drawImage(Score0, 10, 10, 70, 70, this);
			break;
		case 1:
			g.drawImage(Score1, 10, 10, 70, 70, this);
			break;
		case 2:
			g.drawImage(Score2, 10, 10, 70, 70, this);
			break;
		case 3:
			g.drawImage(Score3, 10, 10, 70, 70, this);
			break;
		case 4:
			g.drawImage(Score4, 10, 10, 70, 70, this);
			break;
		default:
			g.drawImage(Score5, 10, 10, 70, 70, this);
			g.drawImage(win, 100, 300, 200, 70, this);
			g.drawImage(lose, 640, 300, 200, 70, this);
			background.gamemode = false;
			break;
		}

		switch (p2_score) {
		case 0:
			g.drawImage(Score0, 840, 10, 70, 70, this);
			break;
		case 1:
			g.drawImage(Score1, 840, 10, 70, 70, this);
			break;
		case 2:
			g.drawImage(Score2, 840, 10, 70, 70, this);
			break;
		case 3:
			g.drawImage(Score3, 840, 10, 70, 70, this);
			break;
		case 4:
			g.drawImage(Score4, 840, 10, 70, 70, this);
			break;
		default:
			g.drawImage(Score5, 840, 10, 70, 70, this);
			g.drawImage(win, 640, 300, 200, 70, this);
			g.drawImage(lose, 100, 300, 200, 70, this);
			background.gamemode = false;
			break;
		}
		reset = false;
	}

	public void Back(Graphics g) {
		g.drawImage(back1, 0, 0, 933, 700, this); // 배경
		g.drawImage(back2, 0, 50, 933, 215, this); // 구름
		g.drawImage(back3, 30, 30, 167, 165, this); // 햇님
		g.drawImage(back4, 451, 375, 28, 295, this); // 네트
	}

	public void Ball(Graphics g) {
		g.drawImage(ball, ball_x, ball_y, 60, 60, this);
	}

	public void P1(Graphics g) {
		if (p1_motion == 1) {
			g.drawImage(player1_jump, p1_x, p1_y, 80, 137, this);
		} else if (p1_motion == 2) {
			g.drawImage(player_leftslide, p1_x, p1_y, 80, 137, this);
		} else if (p1_motion == 3) {
			g.drawImage(player_rightslide, p1_x, p1_y, 80, 137, this);
		} else {
			g.drawImage(player1, p1_x, p1_y, 80, 137, this);
		}
	}

	public void P2(Graphics g) {

		if (p2_motion == 1) {
			g.drawImage(player2_jump, p2_x, p2_y, 80, 137, this);
		} else if (p2_motion == 2) {
			g.drawImage(player_leftslide, p2_x, p2_y, 80, 137, this);
		} else if (p2_motion == 3) {
			g.drawImage(player_rightslide, p2_x, p2_y, 80, 137, this);
		} else {
			g.drawImage(player2, p2_x, p2_y, 80, 137, this);
		}
	}

	public void setBGM() {
		if (GameState == true) {
			try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.addLineListener(this);
				clip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			clip.close();
		}
	}

	public void update(LineEvent e) {
		if (e.getType() == LineEvent.Type.STOP) {
			setBGM();
		}
	}
}