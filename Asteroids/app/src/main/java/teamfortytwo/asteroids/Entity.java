package teamfortytwo.asteroids;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by BrandonWebster on 4/19/15.
 */
public class Entity {

    protected Drawable image; //This is the image drawn to the screen
    protected Vector pos; //The position of the Bee on the screen
    protected int size; //this will be added to position in setBounds
    protected int team;
    protected int speed;
    protected Collisions collisions;
    protected GameView view;
    protected Rect bounds;

    public Entity(Collisions collisions, GameView view, Vector pos, int size){
        this.size = size;
        this.view = view;
        this.pos = pos; //sets the position vector
        this.collisions = collisions;
        this.bounds = new Rect(pos.getX(), pos.getY(), pos.getX() + size, pos.getY() + size);
    }

    //Get Functions
    public Vector getPos(){
        return pos;
    }
    public Drawable getImage(){
        return image;
    }
    public int getSize(){
        return size;
    }
    public Rect getBounds(){ return bounds; }
    public int getTeam() { return team; }

    public void moveAtSpeed(){

            addPos(0, speed);

        }



    //Set Functions
    public int addPos(float dx, float dy){
        /*
        Set the position of the entity
         */



        pos.setX((int) dx + pos.getX());
        pos.setY((int) dy + pos.getY());
        int check = collisions.check(this);

        if(check == Collisions.off_x)
            pos.setX(pos.getX() - (int) dx);
        if(check == Collisions.off_y) {
            pos.setY(pos.getY() - (int) dy);
        }
        if(check == Collisions.colliding) {
            Log.i("Entity", "Collision with entity " + this);
            destroy();
        }



        return check;
    }

    protected void setBounds(int left, int top, int right, int bottom){
        //initializes the image onto the screen, android has 0, 0 as the top left corner
        image.setBounds(left, top, right, bottom);
        bounds = image.getBounds();
    }


    public void draw(Canvas canvas){
        setBounds(pos.getX(), pos.getY(), pos.getX() + size, pos.getY() + size); //updates the position of the image on the screen
        image.draw(canvas); //Draws the image
    }

    public void destroy(){
        view.destroyEntity(this);

    }


}
