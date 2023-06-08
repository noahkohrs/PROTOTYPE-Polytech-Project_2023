package info3.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Viewport {
    Game game ;
    static Viewport viewport ;
    int CamX, CamY ;
    int CamWidth, CamHeight;
    

    public Viewport(Game game) {
        this.game = game;
        viewport = this ;
    }

    static int getWindowsWidth() {
        return Window.getWindows()[0].getWidth() ;
    }
    static int getWindowsHeight() {
        return Window.getWindows()[0].getHeight() ;
    }

    static int OnCamViewX(int x) {
        return x - viewport.CamX ;
    }
    static int OnCamViewY(int y) {
        return y - viewport.CamY ;
    }

    void tick(long elapsed) {
        int precCamX = CamX ;
        int precCamY = CamY ;
        int precCamWidth = CamWidth ;
        int precCamHeight = CamHeight ;

        int p1X = centeredCoordinateX(game.m_cowboy) ;
        int p1Y = centeredCoordinateY(game.m_cowboy) ;
        int p2X = centeredCoordinateX(game.m_cowboy2) ;
        int p2Y = centeredCoordinateY(game.m_cowboy2) ;

        int centerX = (p1X + p2X)/2 ;
        int centerY = (p1Y + p2Y)/2 ;

        CamWidth = Math.abs(p1X-p2X) + 100;
        CamHeight = Math.abs(p1Y-p2Y) + 100;

        if (CamWidth < getWindowsWidth()/4) CamWidth = getWindowsWidth()/4 ;
        if (CamHeight < getWindowsHeight()/4) CamHeight = getWindowsHeight()/4 ;

        if (CamX < 0) {
            CamWidth += CamX ;
            CamX = 0 ;
        }
        if (CamY < 0) {
            CamHeight += CamY ;
            CamY = 0 ;
        }



        if (CamWidth < CamHeight*game.ratio) CamWidth = (int)(CamHeight*game.ratio) ;
        else CamHeight = (int)(CamWidth/game.ratio) ;

        if (CamX + CamWidth > game.m_canvas.getWidth()) {
            CamWidth = game.m_canvas.getWidth() - CamX ;
        }
        if (CamY + CamHeight > game.m_canvas.getHeight()) {
            CamHeight = game.m_canvas.getHeight() - CamY ;
        }

        CamX = centerX - CamWidth/2 ;
        CamY = centerY - CamHeight/2 ;

        CamX = precCamX + (CamX - precCamX)/10 ;
        CamY = precCamY + (CamY - precCamY)/10 ;

        CamWidth = precCamWidth + (CamWidth - precCamWidth)/10 ;
        CamHeight = precCamHeight + (CamHeight - precCamHeight)/10 ;


    }
    int centeredCoordinateX(Cowboy cowboy) {
        return cowboy.m_x + cowboy.m_images[0].getWidth()/2 ;
    }
    int centeredCoordinateY(Cowboy cowboy) {
        return cowboy.m_y + cowboy.m_images[0].getHeight()/2 ;
    }

    void evalViewportLocation() {

    }

    void paint(Graphics g) {
        int cX = centeredCoordinateX(game.m_cowboy) ;
        int cY = centeredCoordinateY(game.m_cowboy) ;
        g.setColor(Color.WHITE);
        g.fillRect(CamX, CamY, CamWidth, CamHeight);
        g.setColor(Color.BLACK);
        g.fillRect(cX-10, cY-10, 20, 20);
    }

    
}
