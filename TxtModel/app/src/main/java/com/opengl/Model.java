package com.opengl;

import java.nio.FloatBuffer;

public class Model {

    private int pointCount;

    private int length;

    private float[] verts;

    private float[] colors;

    private FloatBuffer vertBuffer;

    private FloatBuffer colorBuffer;

    //以下分别保存所有点在x,y,z方向上的最大值、最小值
    float maxX;
    float minX;
    float maxY;
    float minY;
    float maxZ;
    float minZ;

    //返回模型的中心点
    public Point getCentrePoint() {
        float cx = (maxX + minX) / 2;
        float cy = (maxY + minY) / 2;
        float cz = (maxZ + minZ) / 2;
        return new Point(cx, cy, cz);
    }

    //包裹模型的最大半径
    public float getR() {
        float dx = (maxX - minX)/2;
        float dy = (maxY - minY)/2;
        float dz = (maxZ - minZ)/2;
        float max = dx;
        if (dy > max)
            max = dy;
        if (dz > max)
            max = dz;
        return max;
    }


    public int getPointCount() {
        return pointCount;
    }

    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
    }

    public int getLength() { return length;}
    public void setLength(int length) {this.length = length;}
   //顶点属性
    public float[] getVerts() {
        return verts;
    }

    public void setVerts(float[] verts) {
        this.verts = verts;
        vertBuffer = Util.floatToBuffer(verts);
    }



    public float[] getColors() {
        return colors;
    }

    public void setColors(float[] colors) {
        this.colors = colors;
        colorBuffer = Util.floatToBuffer(colors);
    }


    public FloatBuffer getVertBuffer() {
        return vertBuffer;
    }

    public FloatBuffer getColorBuffer() {
        return colorBuffer;
    }



}
