/*
 * Player1을 관리하는 스레드 클래스
 */
public class Player1 extends Thread {
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
	
	public void move() {
		while (background.p1_up == true || background.p1_down == true
				|| background.p1_left == true || background.p1_right == true) {
			if (background.p1_up == true && background.p1_left) {
				background.mg.p1_motion = 1;
				for (int i = 0; i < 40; i++) {
					if(background.mg.reset == true) break;
					background.mg.p1_y -= 8;
					if (background.mg.p1_x > 0) {
						background.mg.p1_x -= 2;
					}
					try {
						this.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 40; i++) {
					if(background.mg.reset == true) break;
					try {
						background.mg.p1_y += 8;
						this.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				background.mg.p1_jump = false;
				background.mg.p1_motion = 0;
			} else if (background.p1_up == true && background.p1_right) {
				background.mg.p1_motion = 1;
				for (int i = 0; i < 40; i++) {
					if(background.mg.reset == true) break;
					background.mg.p1_y -= 8;
					if (background.mg.p1_x < 370) {
						background.mg.p1_x += 2;
					}
					try {
						this.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 40; i++) {
					if(background.mg.reset == true) break;
					try {
						background.mg.p1_y += 8;
						this.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				background.mg.p1_jump = false;
				background.mg.p1_motion = 0;
			} else if (background.p1_up == true) { // 위로
				background.mg.p1_motion = 1;
				for (int i = 0; i < 40; i++) {
					if(background.mg.reset == true) break;
					background.mg.p1_y -= 8;
					try {
						this.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 0; i < 40; i++) {
					if(background.mg.reset == true) break;
					try {
						background.mg.p1_y += 8;
						this.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				background.mg.p1_jump = false;
				background.mg.p1_motion = 0;
			} else if (background.p1_down == true
					&& background.p1_right == true) {
				if (background.mg.p1_x < 300) {
					background.mg.p1_x += 80;
					background.mg.p1_motion = 3;
					background.mg.repaint();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					background.mg.p1_motion = 0;
				}
			} else if (background.p1_down == true && background.p1_left == true) {
				if (background.mg.p1_x > 70) {
					background.mg.p1_x -= 80;
					background.mg.p1_motion = 2;
					background.mg.repaint();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					background.mg.p1_motion = 0;
				}
			} else if (background.p1_left == true) {
				if (background.mg.p1_x > 0) {
					background.mg.p1_x -= 10;
				}
			} else if (background.p1_right == true) {
				if (background.mg.p1_x < 370) {
					background.mg.p1_x += 10;
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
