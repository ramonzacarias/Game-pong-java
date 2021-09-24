package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball{
	
	public double x,y, dx, dy;
	public int width, height, p1,p2;
	public double speed = 1.5;
	
	public Ball(int x, int y) {
		
		this.x = x;
		this.y = y;
		this.width = 5;
		this.height = 5;
		int angle = new Random().nextInt(120 - 45) + 46;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
		
	}
	
	
	public void start() {
		
		this.x = 100;
		this.y = Game.HEIGHT/2-1;
		this.width = 5;
		this.height = 5;
		int angle = new Random().nextInt(120 - 45) + 46;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
		
	}
	
	
	public void tick() {
		
		if (x+(dx*speed)+width >= Game.WIDTH) {
			dx*= -1;
		}else if (x+(dx*speed) < 0) {
			dx*=-1;
		}
		
		if (y >= Game.HEIGHT) {
			
			//Ponto do inimigo
			System.out.println("Ponto do inimigo");
			p2++;
			speed = 1.5;
			start();
			
		}else if (y < 0) {
			//Ponto do jogador
			System.out.println("Ponto do jogador");
			p1++;
			speed = 2.5;
			start();
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),width,height);
		Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.width,Game.enemy.height);
		
		if (bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(120 - 45) + 46;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if (dy > 0) {
				dy*= - 1;
				speed+=0.1;
			}
		}else if (bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(120 - 45) + 46;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if (dy < 0) {
				dy*= - 1;
				speed+=0.1;
			}
		}
		
		x+=dx*speed;
		y+=dy*speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval((int) x, (int) y, width, height);
	}
	
}
