package sample;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.ImageWindow;
import ij.gui.ProfilePlot;
import ij.plugin.frame.ContrastAdjuster;
import ij.process.ImageProcessor;
import javafx.event.ActionEvent;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Controller {
    File pic5= new File("D://screen4.tif");
    ImagePlus imp2 = IJ.openImage(pic5.getPath());
    ImageProcessor ip = imp2.getProcessor();
    public void showPic(ActionEvent actionEvent) {
        //File pic5= new File("D://screen4.tif");
        //ImagePlus imp2 = IJ.openImage(pic5.getPath());
       // ImageProcessor ip = imp2.getProcessor();
        imp2.show();
    }

    public void plotProfileAct(ActionEvent actionEvent) {
        ImageWindow iw =new ImageWindow(imp2);
        //iw.setSize(1000,1000);


        iw.getCanvas().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println(imp2.getRoi().getBounds().getX());
                // iw.getCanvas().getGraphics().getClip().getBounds().getX();
                //imp2.getRoi().getBounds().getX();
                // Roi.addRoiListener();
                int x = (int)imp2.getRoi().getBounds().getX();
                int y =(int)imp2.getRoi().getBounds().getY();
                int h = (int)imp2.getRoi().getBounds().getBounds().getHeight();
                int w = (int)imp2.getRoi().getBounds().getBounds().getWidth();
                BufferedImage ri = imp2.getBufferedImage().getSubimage(x,y,w,h);//getSubimage(90,90, 300,400);
                File froi = new File("D://screenroi.tif");
                try {

                    ImageIO.write(ri, "TIFF", froi );
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
               // ImagePlus improi = IJ.openImage(froi.getPath());
               // improi.show();
                // improi.getPlot().show();
                // improi.plot
                new ProfilePlot(imp2).createWindow();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void contrastAct(ActionEvent actionEvent) {
        IJ.runPlugIn(imp2,new ContrastAdjuster().getClass().getName(),"");
    }
}
