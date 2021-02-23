package com.berat.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {
//cizim icin fonskiyon
	SpriteBatch batch ;
Texture background;
Texture bird;
Texture ufo1,ufo2,ufo3;
BitmapFont font;
int score=0;
int scoredEnemy=0;
	float birdX,birdY;
	int gameState=0;
	float velocity=0;
	float gravity=0.1f;
float enemyVelocity=5;
	 int numberOfEnemies=4;
	 Random random;
	 float[]enemyX=new float[numberOfEnemies]; //ufo gruplarinin x koordinati
	float enemyOffSet[]=new float[numberOfEnemies];
	float enemyOffSet2[]=new float[numberOfEnemies];
	float enemyOffSet3[]=new float[numberOfEnemies];
	 float distance=0; //ufo gruplari arasindaki mesafe
	Circle  birdCircle;
	Circle[] enemyCircle;
	Circle[] enemyCircle2;
	Circle[] enemyCircle3;
ShapeRenderer shapeRenderer;

	@Override
	public void create () {
//oncreatenin aynisi 1 kere olanlar
	batch=new SpriteBatch();
background=new Texture("background.jpg");
shapeRenderer=new ShapeRenderer();
font=new BitmapFont();
font.setColor(Color.WHITE);
font.getData().setScale(4);
bird=new Texture("bird.png");
 birdX=Gdx.graphics.getWidth()/2-Gdx.graphics.getHeight()/2;
birdY=Gdx.graphics.getHeight()/3;
		gameState=1;
birdCircle=new Circle();
	ufo1=new Texture("ufo.png");
		ufo2=new Texture("ufo.png");
		ufo3=new Texture("ufo.png");
distance=Gdx.graphics.getWidth()/2;
		enemyCircle=new Circle[numberOfEnemies];
		enemyCircle2=new Circle[numberOfEnemies];
		enemyCircle3=new Circle[numberOfEnemies];

		random=new Random();
for(int i=0;i<numberOfEnemies;i++){
	enemyOffSet[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
	enemyOffSet2[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
	enemyOffSet3[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

	enemyCircle[i]=new Circle();
	enemyCircle2[i]=new Circle();
	enemyCircle3[i]=new Circle();




	enemyX[i]=Gdx.graphics.getWidth()-ufo1.getWidth()/2+i*distance;



}

	}

	@Override
	public void render () {

		batch.begin();
		//ekrana background cizdirdik (resim,koordinatlar,uzunlugu al ,genisigi al)
		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if(gameState==1){
		if(enemyX[scoredEnemy]<Gdx.graphics.getWidth()/2-Gdx.graphics.getHeight()/2){
			score++;
		if(scoredEnemy<numberOfEnemies-1){
			scoredEnemy++;}
			else {
				scoredEnemy=0;

		}
		}



			if (Gdx.input.justTouched()){
				velocity=-7;
			}
for(int i=0;i<numberOfEnemies;i++){
if(enemyX[i]<Gdx.graphics.getWidth() / 15){
	enemyOffSet[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
	enemyOffSet2[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
	enemyOffSet3[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

	enemyX[i]=enemyX[i]+numberOfEnemies*distance;
}
else{enemyX[i]=enemyX[i]-enemyVelocity;}

	batch.draw(ufo1,enemyX[i] , Gdx.graphics.getHeight()/2+enemyOffSet[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
	batch.draw(ufo2,enemyX[i] ,Gdx.graphics.getHeight()/2+enemyOffSet2[i] , Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
	batch.draw(ufo3, enemyX[i],Gdx.graphics.getHeight()/2+enemyOffSet3[i] , Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);

	enemyCircle[i] = new Circle(enemyX[i]+Gdx.graphics.getWidth() / 30,Gdx.graphics.getHeight()/2+enemyOffSet[i]+Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() /30);
	enemyCircle2[i] = new Circle(enemyX[i]+Gdx.graphics.getWidth() / 30,Gdx.graphics.getHeight()/2+enemyOffSet2[i]+Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);
	enemyCircle3[i] = new Circle(enemyX[i]+Gdx.graphics.getWidth() / 30,Gdx.graphics.getHeight()/2+enemyOffSet3[i]+Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() / 30);
}



		}
//kus ekrandan dusmesin diye if kontrolu
		if(gameState==1){
			//eger oyun baslamissa kusu asagiya dusur
			if(birdY>0){
{velocity=velocity+gravity;
	birdY=birdY-velocity;}}
		if(birdY<0){
			gameState=2;
		}
		}

		else{
			if(Gdx.input.justTouched()){
				gameState=1;
				birdY=Gdx.graphics.getHeight()/3;
				for(int i=0;i<numberOfEnemies;i++){
					enemyOffSet[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					enemyOffSet2[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);
					enemyOffSet3[i]=(random.nextFloat()-0.5f)*(Gdx.graphics.getHeight()-200);

					enemyCircle[i]=new Circle();
					enemyCircle2[i]=new Circle();
					enemyCircle3[i]=new Circle();


					enemyX[i]=Gdx.graphics.getWidth()-ufo1.getWidth()/2+i*distance;

				}
velocity=0;
				score=0;
				scoredEnemy=0;

			}
		}


		// oyun devam ettigi surece cagirilan method

	batch.draw(bird,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
	font.draw(batch,String.valueOf(score),100,200);
		batch.end();
	birdCircle.set(birdX, birdY,Gdx.graphics.getWidth()/30);
	/*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
	shapeRenderer.setColor(Color.BLACK);
	shapeRenderer.circle(birdCircle.x+Gdx.graphics.getWidth()/30,birdCircle.y+Gdx.graphics.getHeight()/20,birdCircle.radius);*/

	for(int i=0;i<numberOfEnemies;i++){
/*shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth() / 30,Gdx.graphics.getHeight()/2+enemyOffSet[i]+Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() /30);
		shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth() / 30,Gdx.graphics.getHeight()/2+enemyOffSet2[i]+Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() /30);
		shapeRenderer.circle(enemyX[i]+Gdx.graphics.getWidth() / 30,Gdx.graphics.getHeight()/2+enemyOffSet3[i]+Gdx.graphics.getHeight() / 20,Gdx.graphics.getWidth() /30);*/
if(Intersector.overlaps(birdCircle,enemyCircle[i])||Intersector.overlaps(birdCircle,enemyCircle2[i])||Intersector.overlaps(birdCircle,enemyCircle3[i])){
gameState=2;
}

	}
	//shapeRenderer.end();
	}
	
	@Override
	public void dispose () {

	}
}
