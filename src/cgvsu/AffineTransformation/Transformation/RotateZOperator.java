package cgvsu.AffineTransformation.Transformation;

import javax.vecmath.Matrix4f;

public class RotateZOperator implements MatrixOperator {
    private final Matrix4f transformMatrix;

    public RotateZOperator(float radians) {
        transformMatrix = new Matrix4f();
        transformMatrix.setIdentity();

        transformMatrix.m00 = (float) Math.cos(radians);
        transformMatrix.m01 = (float) Math.sin(radians);
        transformMatrix.m10 = (float) Math.sin(radians)*(-1);
        transformMatrix.m11 = (float) Math.cos(radians);

    }

    @Override
    public Matrix4f getTransformMatrix() {
        return transformMatrix;
    }
}
