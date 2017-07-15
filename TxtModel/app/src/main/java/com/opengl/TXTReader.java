package com.opengl;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class TXTReader {

    int length ;
    public Model parserBinStlInSDCard(String path) throws IOException {
        Model model = new Model();
        File file = new File(path);
        int pointcount = 0;

        if(file.isFile() && file.exists())
        {
            //Log.i("TAG","_______________________文件存在______________________________");
            pointcount = getTxtLinage(path);

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            //Log.i("TAG","_______________________打开文件流______________________________");
            String lineTxt = null;

            float[] verts = new float[pointcount * 3];//3*3*4字节
            float[] colors = new float[pointcount * 4];
            for (int i = 0;i<pointcount;i++) {
                lineTxt = br.readLine();
                //Log.i("TAG","_______________________源文件______________________________");
                //System.out.println(lineTxt);
                String[] message = lineTxt.split(" ");
                verts[3 * i] = Util(message[0]);
                verts[3 * i + 1] = Util(message[1]);
                verts[3 * i + 2] = Util(message[2]);
                Log.i("TAG",verts[3 * i]+" "+verts[3 * i+1]+" "+verts[3 * i+2]);


                if(length==7){
                    colors[4 * i+3] = Float.parseFloat(message[6]);
                    if(colors[3]<1) {
                        colors[4 * i] = Float.parseFloat(message[3]);
                        colors[4 * i + 1] = Float.parseFloat(message[4]);
                        colors[4 * i + 2] = Float.parseFloat(message[5]);
                        colors[4 * i + 3] = 1;
                    }
                    else {
                        colors[4 * i] = Float.parseFloat(message[3]) / 255;
                        colors[4 * i + 1] = Float.parseFloat(message[4]) / 255;
                        colors[4 * i + 2] = Float.parseFloat(message[5]) / 255;
                        colors[4 * i + 3] = 1;
                    }
                }
                else if(length==6) {
                    colors[4 * i] = Float.parseFloat(message[3]);
                    if(colors[0]<1) {
                        colors[4 * i] = Float.parseFloat(message[3]);
                        colors[4 * i + 1] = Float.parseFloat(message[4]);
                        colors[4 * i + 2] = Float.parseFloat(message[5]);
                        colors[4 * i + 3] = 1;
                    }
                    else {
                        colors[4 * i] = Float.parseFloat(message[3]) / 255;
                        colors[4 * i + 1] = Float.parseFloat(message[4]) / 255;
                        colors[4 * i + 2] = Float.parseFloat(message[5]) / 255;
                        colors[4 * i + 3] = 1;
                    }
                }
                else if(length==3) {
                    colors[4 * i] = 0;
                    colors[4 * i + 1] = 1;
                    colors[4 * i + 2] = 0;
                    colors[4 * i + 3] = 0;
                }
                else ;
                if (i == 0) {
                    //数值初始化
                    model.minX = model.maxX = verts[3 * i];
                    model.minY = model.maxY = verts[3 * i + 1];
                    model.minZ = model.maxZ = verts[3 * i + 2];
                } else {
                    model.minX = Math.min(model.minX, verts[3 * i]);
                    model.minY = Math.min(model.minY, verts[3 * i + 1]);
                    model.minZ = Math.min(model.minZ, verts[3 * i + 2]);
                    model.maxX = Math.max(model.maxX, verts[3 * i]);
                    model.maxY = Math.max(model.maxY, verts[3 * i + 1]);
                    model.maxZ = Math.max(model.maxZ, verts[3 * i + 1]);
                }
                //Log.i("TAG","_______________________坐标值______________________________");
                //System.out.println(verts[3*i]+" "+verts[3*i+1]+" "+verts[3*i+2]+" "+colors[3*i]+" "+colors[3*i+1]+" "+colors[3*i+2]);
            }
            //Log.i("TAG","_______________________共"+pointcount+"行______________________________");
            br.close();

            model.setPointCount(pointcount);
            model.setLength(length);
            model.setVerts(verts);
            model.setColors(colors);
        }
        else Log.i("TAG","_______________________文件不存在______________________________");
        //Log.i("TAG","_______________________返回模型______________________________");
        return model;
    }

    public int getTxtLinage(String path) throws IOException {
        int linenumber = 0;
        File file = new File(path);
        if (file.isFile() && file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String test = null;
            while (br.readLine() != null) {
                if(linenumber==0) {
                    test = br.readLine();
                    String[] msg = test.split(" ");
                    length = msg.length;
                    System.out.println(length);
                }
                linenumber++;
            }
            br.close();
        }
        return linenumber;
    }

    public float Util(String string){
        BigDecimal bd=new BigDecimal(string);
        return bd.setScale(6, BigDecimal.ROUND_HALF_UP).floatValue();
    }

}
