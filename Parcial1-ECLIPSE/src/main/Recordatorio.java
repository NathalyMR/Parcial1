package main;

import java.awt.Color;

import processing.core.PApplet;

public class Recordatorio {

	private PApplet app;
	private int x, y, r,g,b;
	private String msg;
	
	public Recordatorio (int x, int y, int r, int g, int b, String msg, PApplet app) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.g = g;
		this.b = b;
		this.msg = msg;
		this.app = app;
	}
	
	public void drawRecor() {
		app.fill (255,255,255);
		app.rect(x, y, 160, 60);
		app.fill(r,g,b);
		app.ellipse(x+80, y-10, 40, 40);
		app.fill(0,0,0);
		app.text(msg, x+10, y+10, 140, 40);
		}
	
	public void vistaPrev() {
		app.fill (255,255,255);
		app.rect(x, y, 160, 60);
		app.fill(r,g,b);
		app.ellipse(x+80, y-10, 40, 40);
		app.fill(0,0,0);
		app.text(msg, x+10, y+10, 140, 40);
	}
	
}