package info3.game;

import java.awt.Color;
import java.awt.Graphics;

public class Viewport {
    
    Game game ;
    static Viewport viewport ;
    int CamX, CamY ;
    int CamWidth, CamHeight;
    

    public Viewport(Game game) {
        this.game = game;
        viewport = this ;
    }

    static int getLevelWidth() {
        return Game.game.m_map.realWidth() ;
    }
    static int getLevelHeight() {
        return Game.game.m_map.realHeight() ;
    }

    static int OnCamViewX(int x, double scale) {
        return (int)((x - viewport.CamX)*scale) ;
    }
    static int OnCamViewY(int y, double scale) {
        return (int)((y - viewport.CamY)*scale) ;
    }

    void tick(long elapsed) {

        evalViewportLocation();
    }
    int centeredCoordinateX(Cowboy cowboy) {
        return cowboy.m_x + cowboy.m_images[0].getWidth()/2 ;
    }
    int centeredCoordinateY(Cowboy cowboy) {
        return cowboy.m_y + cowboy.m_images[0].getHeight()/2 ;
    }

    void evalViewportLocation() {
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

        CamWidth = Math.abs(p1X-p2X) + game.m_canvas.getWidth()/8;
        CamHeight = Math.abs(p1Y-p2Y) + game.m_canvas.getHeight()/8;


        if (CamWidth < getLevelWidth()/4) CamWidth = getLevelWidth()/4 ;
        if (CamHeight < getLevelHeight()/4) CamHeight = getLevelHeight()/4 ;


        CamX = centerX - CamWidth/2 ;
        CamY = centerY - CamHeight/2 ;



        
        if (CamX < 0) {
            CamX = 0 ;
        }
        if (CamY < 0) {
            CamY = 0 ;
        }



        if (CamWidth < CamHeight*game.ratio) CamWidth = (int)(CamHeight*game.ratio) ;
        else CamHeight = (int)(CamWidth/game.ratio) ;

        if (CamX + CamWidth > getLevelWidth()) {
            CamX = getLevelWidth() - CamWidth ;
        }
        if (CamY + CamHeight > getLevelHeight()) {
            CamY = getLevelHeight() - CamHeight ;
        }

        int ANIME = 16 ;
        CamX = precCamX + (CamX - precCamX)/ANIME ;
        CamY = precCamY + (CamY - precCamY)/ANIME ;

        CamWidth = precCamWidth + (CamWidth - precCamWidth)/ANIME ;
        CamHeight = precCamHeight + (CamHeight - precCamHeight)/ANIME ;
    }

    static int realSize(int size, double scaleRatioDisplay) {
        return (int)((double)size*scaleRatioDisplay)+1 ;
    }
    void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(CamX, CamY, CamWidth, CamHeight);

    }
    void viewportView(Graphics g, int width) {
        double scaleRatioDisplay = (double)width / (double)CamWidth ;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, game.m_canvas.getHeight());
        game.m_map.viewportView(g, scaleRatioDisplay);
        game.m_cowboy.viewportView(g, scaleRatioDisplay);
        game.m_cowboy2.viewportView(g, scaleRatioDisplay);
    }

    
}
