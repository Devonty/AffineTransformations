package cgvsu.AffineTransformation.Transformation;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

public class ScaleOperator implements MatrixOperator {
    private final Matrix4f transformMatrix;

    public ScaleOperator(float sx, float sy, float sz) {
        transformMatrix = new Matrix4f();
        transformMatrix.setIdentity();
        transformMatrix.m00 = sx;
        transformMatrix.m11 = sy;
        transformMatrix.m22 = sz;
    }

    public ScaleOperator(Vector3f scales) {
        this(scales.x, scales.y, scales.z);
    }

    @Override
    public Matrix4f getTransformMatrix() {
        return transformMatrix;
    }
}
