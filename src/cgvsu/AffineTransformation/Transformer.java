package cgvsu.AffineTransformation;

import cgvsu.AffineTransformation.Transformation.MatrixOperator;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import java.util.List;
import java.util.Vector;

public class Transformer {
    private Matrix4f totalTransformMatrix;

    public Transformer() {
        this.totalTransformMatrix = new Matrix4f();
        // Делаю единичной
        this.totalTransformMatrix.setIdentity();
    }

    public void addTransformation(MatrixOperator matrixOperator){
        totalTransformMatrix.mul(matrixOperator.getTransformMatrix());
    }

    public void transform(Vector4f vector4f) {
        totalTransformMatrix.transform(vector4f);
    }

    public void transform(Vector3f vector3f) {
        Vector4f vector4f = new Vector4f(vector3f.x, vector3f.y, vector3f.z, 1f);
        totalTransformMatrix.transform(vector4f);
        vector3f.x = vector4f.x;
        vector3f.y = vector4f.y;
        vector3f.z = vector4f.z;
    }

    public void transform(List<Vector3f> vertexes) {
        vertexes.forEach(this::transform);
    }

}
