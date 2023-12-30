package cgvsu.AffineTransformation.Transformation;

import javax.vecmath.Matrix4f;

public class RotateXOperator implements MatrixOperator {
    private final Matrix4f transformMatrix;

    public RotateXOperator(float radians) {
        transformMatrix = new Matrix4f();
        transformMatrix.setIdentity();

        transformMatrix.m11 = (float) Math.cos(radians);
        transformMatrix.m12 = (float) Math.sin(radians);
        transformMatrix.m21 = (float) Math.sin(radians)*(-1);
        transformMatrix.m22 = (float) Math.cos(radians);
    }

    @Override
    public Matrix4f getTransformMatrix() {
        return new Matrix4f(transformMatrix);
    }
}
