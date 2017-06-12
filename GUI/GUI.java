package GUI;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by alexander on 2017-05-08.
 */
public class GUI extends Canvas {

    final static double gravityconstant = 9.82;

    public static void main(String[] args) {


        GUI game = new GUI();

        long lastTime = System.nanoTime();
        int ups = 30;
        long ns = 1000000000/ups;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta +=((now-lastTime)/ (double) ns);
            while (delta > 1) {
                //GRAVITY
               // System.out.println(game.yvel);
                if (game.yvel > gravityconstant) {
                    game.y -= (game.yvel-gravityconstant);
                    game.yvel-=.5;
                }
                if ((game.y < 800-img.getHeight()) && game.yvel <= gravityconstant) {
                    game.y+=gravityconstant;
                }
                if (game.xvel > 0) {
                    game.x+=game.xvel;
                    game.xvel-=0.5;
                } else if (game.xvel < 0 && game.x != 0) {
                    game.x+=game.xvel;
                    game.xvel+=0.5;
                }
                if (game.x < 0) {
                    game.x = 0;
                    System.out.println(game.frame.getWidth());
                } else if (game.x > game.frame.getWidth()-game.img.getWidth()) {

                    game.x = game.frame.getWidth()-img.getWidth();
                }
                game.repaint();
                delta--;
            }
            lastTime = now;
        }


    }
    // v = s/t

    JFrame frame;
    static BufferedImage img;
    int width = 800;
    int height = 800;
    int x = 0;
    int y = 700;

    double game_ver = 0.2;

    Shape firstJump;

    Shape guy;

    double yvel = gravityconstant;

    double xvel = 0;


    private Image dbImage;

    private Graphics dbg;


    public GUI() {
        try {
            img = ImageIO.read(new File("/home/alexander/IdeaProjects/Programmering1/src/GUI/sprite.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim);
        frame = new JFrame("Super Game " +  game_ver);
        frame.addKeyListener(new KeyListener());
        frame.setUndecorated(true);
        frame.add(this);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setBackground(Color.BLUE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void uppate(Graphics g) {
        if (dbImage == null) {
            dbImage = createImage(width, height);
            dbg = dbImage.getGraphics();
        }

        dbg.setColor(getBackground());
        dbg.fillRect(0, 0 , width, height);

        dbg.setColor(getForeground());
        paint(dbg);

        g.drawImage(dbImage, 0, 0, this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        firstJump = new Rectangle(0,800,frame.getWidth(),300);
        g.fillRect(0,800,frame.getWidth(),300);
        g.fillRect(200, 600, 100, 25);

        guy = new Rectangle(x, y, img.getWidth(), img.getHeight());

        g.drawImage(img, x, y, this);


    }


    public class KeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent keyEvent) {
            if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                    xvel = 5;

            } else if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                    xvel = -5;

            } else if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                if (yvel == gravityconstant) {
                    yvel += 10;
                }
            }
        }
        public void keyReleased(KeyEvent e) {

        }

        public void keyTyped(KeyEvent e) {

        }
    }



}
