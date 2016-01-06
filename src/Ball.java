/*
 * �豸���� ������ ������ Ŭ���� 
 */

public class Ball extends Thread {
	int count = 0;
	int minus_x1 = 0;
	int minus_x2 = 0;
	int minus_y1 = 0;
	int minus_y2 = 0;

	/* Ball ������ ���� */
	public void run() {
		while (true) {
			if (background.gamemode == false)
				break;
			move();
			try {
				Thread.sleep(10);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/* Ball �����̴� �Լ� */
	public void move() {
		// System.out.println(background.mg.p1_jump+ " " +background.mg.p1_event
		// );
		// System.out.println(background.mg.p2_jump+ " " +background.mg.p2_event
		// );
		if (background.mg.ball_y > 610) { // �ٴ� �ݻ�
			if (background.mg.ball_x < 435) {
				background.mg.getScore(2);
			} else {
				background.mg.getScore(1);
			}
			background.mg.speed_x = 0;
			background.mg.speed_y = 5;
			background.mg.speed_y = -background.mg.speed_y;
			draw();
		}
		/*Player1 ������ ���� ����� ���*/
		else if (background.mg.p1_x - 30 < background.mg.ball_x && background.mg.ball_x < background.mg.p1_x + 70 
				&& background.mg.p1_y < background.mg.ball_y && background.mg.ball_y < background.mg.p1_y + 137) {
						if (background.mg.p1_jump == true && background.mg.p1_event == 1) {
				background.mg.ball_y = background.mg.p1_y;
				background.mg.ball_x = background.mg.p1_x + 80;
				background.mg.speed_x = 9;
				background.mg.speed_y = 2;
				background.mg.p1_event = 0;
				background.mg.p1_jump = false;
			} else if (background.mg.p1_jump == true && background.mg.p1_event == 2) {
				background.mg.speed_x = 5;
				background.mg.speed_y = -10;
				background.mg.p1_event = 0;
				background.mg.p1_jump = false;
			} else {
				background.mg.ball_y = background.mg.p1_y;
				background.mg.speed_y = -Math.abs(background.mg.speed_y);
				draw();
				if (background.mg.ball_touch == false) {
					if (background.p1_left == true) {
						background.mg.speed_x = Math.abs(background.mg.speed_x) - 3;
					} else if (background.p1_right == true) {
						background.mg.speed_x = Math.abs(background.mg.speed_x) + 3;
					}
				}
				background.mg.ball_touch = true;
			}

		} 
		/*Player2 ������ ���� ����� ���*/
		else if (background.mg.p2_x - 30 < background.mg.ball_x && background.mg.ball_x < background.mg.p2_x + 50 
				&& background.mg.p2_y < background.mg.ball_y && background.mg.ball_y < background.mg.p2_y + 137) {
			//System.out.println(background.mg.p2_jump);
			if (background.mg.p2_jump == true && background.mg.p2_event == 1) {
				background.mg.ball_y = background.mg.p2_y;
				background.mg.ball_x = background.mg.p2_x - 30;
				background.mg.speed_x = -9;
				background.mg.speed_y = 2;
				background.mg.p2_event = 0;
				background.mg.p2_jump = false;
			} else if (background.mg.p2_jump == true && background.mg.p2_event == 2) {
				background.mg.speed_x = -5;
				background.mg.speed_y = -10;
				background.mg.p2_event = 0;
				background.mg.p2_jump = false;
			} else {
				background.mg.ball_y = background.mg.p2_y;
				background.mg.speed_y = -Math.abs(background.mg.speed_y);
				if (background.mg.ball_touch == false) {
					if (background.p2_left == true) {
						background.mg.speed_x = Math.abs(background.mg.speed_x) + 3;
					} else if (background.p2_right == true) {
						background.mg.speed_x = Math.abs(background.mg.speed_x) - 3;
						background.mg.speed_x++;
					}
					background.mg.ball_touch = true;
				}
			}
			draw();
		}
		// õ�� �ݻ�
		else if (background.mg.ball_y <= 0) { 
			background.mg.ball_y = 0;

			background.mg.speed_y = -background.mg.speed_y;
			draw();
		} 
		// ���� �� �ݻ�
		else if (background.mg.ball_x <= 0) { 
			background.mg.ball_x = 0;
			background.mg.speed_x = -background.mg.speed_x;
			draw();
		}
		// ������ �� �ݻ�
		else if (background.mg.ball_x > 870) { 
			background.mg.ball_x = 870;
			background.mg.speed_x = -background.mg.speed_x;
			draw();
		}
		//��� �� �ݻ�
		else if (390 < background.mg.ball_x && background.mg.ball_x < 478
				&& 315 < background.mg.ball_y && background.mg.ball_y < 321) {
			background.mg.speed_y = -background.mg.speed_y;
			draw();
		} 
		//��� �� �ݻ�
		else if (390 < background.mg.ball_x && background.mg.ball_x < 478 
				&& 317 < background.mg.ball_y) {
			if (background.mg.speed_x > 0) {
				background.mg.ball_x = 390;
			} else if (background.mg.speed_x < 0) {
				background.mg.ball_x = 478;
			}
			background.mg.speed_x = -background.mg.speed_x * 2;
			draw();
			background.mg.speed_x = background.mg.speed_x / 2;
		} else {
			background.mg.ball_touch = false;
			draw();
		}
		background.mg.repaint();
	}
	//Ball �����̴� �ӵ� �Լ�
	void draw() {
		background.mg.ball_x += background.mg.speed_x;
		background.mg.ball_y += background.mg.speed_y;
	}
}
