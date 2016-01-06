/*
 * Player2을 관리하는 스레드 클래스
 */
public class Player2 extends Thread {
	public void run() {
		while (true) {
			if(background.gamemode == false) break;
			move();
			try {
				Thread.sleep(10);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void move() {
		while (background.p2_up == true || background.p2_down == true
				|| background.p2_left == true || background.p2_right == true) {
			if (background.p2_up == true && background.p2_left) {
				background.mg.p2_motion = 1;
				for (int i = 0; i < 40; i++) {
					if(background.mg.reset == true) break;
					try {
						background.mg.p2_y -= 8;
						if (background.mg.p2_x > 479) {
							background.mg.p2_x -= 2;
						}
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 40; i++) {
					if(background.mg.reset == true) break;
					try {
						background.mg.p2_y += 8;
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				background.mg.p2_jump = false;
				background.mg.p2_motion = 0;
			} else if (background.p2_up == true && background.p2_right) {
				background.mg.p2_motion = 1;
				for (int i = 0; i < 40; i++) {
					if(background.mg.reset == true) break;
					try {
						background.mg.p2_y -= 8;
						if (background.mg.p2_x < 849) {
							background.mg.p2_x += 2;
						}
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 40; i++) {
					if(background.mg.reset == true) break;
					try {
						background.mg.p2_y += 8;
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				background.mg.p2_jump = false;
				background.mg.p2_motion = 0;
			} else if (background.p2_up == true) { // 위로
				background.mg.p2_motion = 1;
				for (int i = 0; i < 40; i++) {
					if(background.mg.reset == true) break;
					try {
						background.mg.p2_y -= 8;
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 40; i++) {
					if(background.mg.reset == true) break;
					try {
						background.mg.p2_y += 8;
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Autdo-generated catch block
						e.printStackTrace();
					}
				}
				background.mg.p2_jump = false;
				background.mg.p2_motion = 0;
			} else if (background.p2_down == true && background.p2_right) {
				if (background.mg.p2_x < 779) {
					background.mg.p2_x += 80;
					background.mg.p2_motion = 3;
					background.mg.repaint();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					background.mg.p2_motion = 0;
				}
			} else if (background.p2_down == true && background.p2_left) {

				if (background.mg.p2_x > 539) {
					background.mg.p2_x -= 80;
					background.mg.p2_motion = 2;
					background.mg.repaint();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					background.mg.p2_motion = 0;
				}
			} else if (background.p2_left == true) {
				if (background.mg.p2_x > 479) {
					background.mg.p2_x = background.mg.p2_x - 10;
				}
			} else if (background.p2_right == true) {
				if (background.mg.p2_x < 849) {
					background.mg.p2_x = background.mg.p2_x + 10;
				}
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
