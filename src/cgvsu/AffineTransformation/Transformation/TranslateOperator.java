package cgvsu.AffineTransformation.Transformation;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

public class TranslateOperator implements MatrixOperator {
    private final Matrix4f transformMatrix;

    public TranslateOperator(float sx, float sy, float sz) {
        transformMatrix = new Matrix4f();
        transformMatrix.setIdentity();


        transformMatrix.m03 = sx;
        transformMatrix.m13 = sy;
        transformMatrix.m23 = sz;
    }

    public TranslateOperator(Vector3f translation) {
        this(translation.x, translation.y, translation.z);
    }

    @Override
    public Matrix4f getTransformMatrix() {
        return transformMatrix;
    }
}
