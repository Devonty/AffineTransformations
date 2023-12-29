package cgvsu.AffineTransformation;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.*;
import cgvsu.model.Model;

public class AffineTransformations {

    public static ArrayList<Vector3f> rotate(ArrayList<Vector3f> vertices, Vector3f angles) {
        // Применить поворот ко всем вершинам

        Matrix4f rotationXMatrix = new Matrix4f();
        Matrix4f rotationYMatrix = new Matrix4f();
        Matrix4f rotationZMatrix = new Matrix4f();
        Matrix4f rotationMatrix = new Matrix4f();

        rotationXMatrix.setIdentity();
        rotationYMatrix.setIdentity();
        rotationZMatrix.setIdentity();
        rotationMatrix.setIdentity();


        rotationXMatrix.m11 = (float) Math.cos(angles.x);
        rotationXMatrix.m12 = (float) Math.sin(angles.x);
        rotationXMatrix.m21 = (float) Math.sin(angles.x)*(-1);
        rotationXMatrix.m22 = (float) Math.cos(angles.x);

        rotationYMatrix.m00 = (float) Math.cos(angles.y);
        rotationYMatrix.m02 = (float) Math.sin(angles.y);
        rotationYMatrix.m20 = (float) Math.sin(angles.y)*(-1);
        rotationYMatrix.m22 = (float) Math.cos(angles.y);

        rotationZMatrix.m00 = (float) Math.cos(angles.z);
        rotationZMatrix.m01 = (float) Math.sin(angles.z);
        rotationZMatrix.m10 = (float) Math.sin(angles.z)*(-1);
        rotationZMatrix.m11 = (float) Math.cos(angles.z);

        rotationMatrix.mul(rotationXMatrix);
        rotationMatrix.mul(rotationYMatrix);
        rotationMatrix.mul(rotationZMatrix);

        ArrayList<Vector3f> newVectors = new ArrayList<>(vertices.size());

        for (Vector3f vertex: vertices){
            Vector3f vector3f = new Vector3f(vertex);
            rotationMatrix.transform(vector3f);
            newVectors.add(vector3f);
        }

        return newVectors;
    }
    public static ArrayList<Vector3f> scale(ArrayList<Vector3f> vertices,Vector3f scaleVector) {
        Matrix4f scaleMatrix = new Matrix4f();
        scaleMatrix.setIdentity();

        scaleMatrix.m00 = scaleVector.x;
        scaleMatrix.m11 = scaleVector.y;
        scaleMatrix.m22 = scaleVector.z;


        ArrayList<Vector3f> newVectors = new ArrayList<>(vertices.size());

        for (Vector3f vertex : vertices) {
            Vector3f vector3f = new Vector3f(vertex);
            scaleMatrix.transform(vector3f);
            newVectors.add(vector3f);
        }

        return newVectors;
    }
    public static ArrayList<Vector3f> translate(ArrayList<Vector3f> vertices, Vector3f translationVector) {
        // Применить вектор перемещения ко всем вершинам
        Matrix4f translateMatrix = new Matrix4f();
        translateMatrix.setIdentity();


        translateMatrix.m03 = translationVector.x;
        translateMatrix.m13 = translationVector.y;
        translateMatrix.m23 = translationVector.z;

        ArrayList<Vector3f> newVectors = new ArrayList<>(vertices.size());

        for (Vector3f vertex : vertices) {
            Vector4f vertex4f =  new Vector4f(vertex.x, vertex.y, vertex.z,1);
            translateMatrix.transform(vertex4f);
            newVectors.add(new Vector3f(vertex4f.x, vertex4f.y, vertex4f.z));

        }

        return newVectors;
    }

    public static ArrayList<Vector3f> scaleRotateTranslate(ArrayList<Vector3f> vertices, Vector3f scales, Vector3f rotation, Vector3f transportation){
        return AffineTransformations.translate(
                AffineTransformations.rotate(
                        AffineTransformations.scale(vertices, scales),
                        rotation
                ),
                transportation
        );
    }
}
