package cgvsu.AffineTransformation.Transformation;

import javax.vecmath.Matrix4f;

public class RotateYOperator implements MatrixOperator {
    private final Matrix4f transformMatrix;

    public RotateYOperator(float radians) {
        transformMatrix = new Matrix4f();
        transformMatrix.setIdentity();

        transformMatrix.m00 = (float) Math.cos(radians);
        transformMatrix.m02 = (float) Math.sin(radians);
        transformMatrix.m20 = (float) Math.sin(radians)*(-1);
        transformMatrix.m22 = (float) Math.cos(radians);
    }

    @Override
    public Matrix4f getTransformMatrix() {
        return new Matrix4f(transformMatrix);
    }
}
