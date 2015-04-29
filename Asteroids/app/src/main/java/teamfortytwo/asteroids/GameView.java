package teamfortytwo.asteroids;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by BrandonWebster on 4/22/15.
 */

public class GameView extends View implements ValueAnimator.AnimatorUpdateListener{
    /* This class is where drawables actually get updated and drawn. */
    private Bee player;
    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private Collisions collisions;
    private Resources res;

    private int score = 0;

    public int frame = 0;

    public static Random random;

    private GameScreen gameScreen;
    private int screenWidth, screenHeight;

    public GameView(Context context) {
        super(context);

        gameScreen = (GameScreen) context;
        this.screenHeight = GameScreen.screenHeight;
        this.screenWidth = GameScreen.screenWidth;

        random = new Random(System.currentTimeMillis());

        res = this.getResources();
        Drawable background = res.getDrawable(R.drawable.ingame);
        setBackground(background);

        collisions = new Collisions(this);
        player = new Bee(res, collisions, this, new Vector(screenWidth / 2, screenHeight / 10), screenWidth / 12);



//        Timer timer = new Timer();
//
//        timer.scheduleAtFixedRate(new CreateEnemies(this), 5000, 500);
//        timer.scheduleAtFixedRate(new MoveEntities(), 3000, 30);
    }

    public void setFrame(int frame){
        this.frame = frame;
    }
    public int getFrame(){ return frame; }
    public int getScore(){ return score; }

    public void updatePlayer(float angle){
        float move = angle * (screenWidth / 80);
        player.move(move);

    }
    public void shoot(){
        Vector newPos = player.getPos().copy();
        newPos.set(player.getPos().getX() + 3 * player.getSize() / 4, player.getPos().getY() + 3 * player.getSize() / 4);
        entities.add(new Bullet(res, collisions, this, 0, newPos, screenWidth / 24));
    }
    public void shoot(Bullet bullet){
        entities.add(bullet);
    }


    public void destroyEntity(Entity entity){
        if(entity.equals(player)){
            gameScreen.endGame();
        }
        entities.remove(entity);
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        for(int i = 0; i < entities.size(); i++){
            entities.get(i).draw(canvas);

        }
        player.draw(canvas);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {

        if(animation.getAnimatedValue() == 0){
            entities.add(new Caterpillar(res, collisions, this, new Vector(random.nextInt() % screenWidth, screenHeight), screenWidth / 12));
            score += 1;
        }
        for(int i = 0; i < entities.size(); i++){
                entities.get(i).update(frame);
        }
    }

}
