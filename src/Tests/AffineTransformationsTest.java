package Tests;

import cgvsu.model.Model;
import cgvsu.objreader.IncorrectFileException;
import cgvsu.objreader.ObjReader;
import cgvsu.objreader.PathReadException;

import javax.vecmath.Vector3f;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static cgvsu.AffineTransformation.AffineTransformations.*;
import static org.junit.jupiter.api.Assertions.*;

class AffineTransformationsTest {
    public static final String filepath = "Objects\\WrapHead.obj";

    @org.junit.jupiter.api.Test
    void testRotateObjectX() throws PathReadException, IncorrectFileException {
        // Считываем модель
        Model model = getModel(filepath);

        // получем ожидаемую вершину
        Vector3f expectedVector = new Vector3f(model.vertices.get(0));
        expectedVector.z *= -1;
        expectedVector.y *= -1;

        // получаем список новых вершин
        List<Vector3f> newVertexes = rotateX(model.vertices, (float) Math.PI);
        // проверяем ожидаемую вершину с погрешностью
        assertTrue(expectedVector.epsilonEquals(newVertexes.get(0), 1E-4f));

    }

    @org.junit.jupiter.api.Test
    void testRotateObjectY() throws PathReadException, IncorrectFileException {
        // Считываем модель
        Model model = getModel(filepath);

        Vector3f expectedVector = new Vector3f(model.vertices.get(0));
        expectedVector.x *= -1;
        expectedVector.z *= -1;

        List<Vector3f> newVertexes = rotateY(model.vertices, (float) Math.PI);
        // проверяем ожидаемую вершину с погрешностью
        assertTrue(expectedVector.epsilonEquals(newVertexes.get(0), 1E-4f));

    }
    @org.junit.jupiter.api.Test
    void testRotateObjectZ() throws PathReadException, IncorrectFileException {
        // Считываем модель
        Model model = getModel(filepath);

        Vector3f expectedVector = new Vector3f(model.vertices.get(0));
        expectedVector.x *= -1;
        expectedVector.y *= -1;

        List<Vector3f> newVertexes = rotateZ(model.vertices, (float) Math.PI);
        // проверяем ожидаемую вершину с погрешностью
        assertTrue(expectedVector.epsilonEquals(newVertexes.get(0), 1E-4f));

    }

    @org.junit.jupiter.api.Test
    void testRotateObjectXYZ() throws PathReadException, IncorrectFileException {
        // Считываем модель
        Model model = getModel(filepath);

        Vector3f expectedVector = new Vector3f(model.vertices.get(0));
        expectedVector.x *= -1;
        expectedVector.y *= -1;

        List<Vector3f> newVertexes = rotateXYZ(model.vertices, new Vector3f(0f, 0f, (float) Math.PI));
        // проверяем ожидаемую вершину с погрешностью
        assertTrue(expectedVector.epsilonEquals(newVertexes.get(0), 1E-4f));

    }

    @org.junit.jupiter.api.Test
    void testScaleObject()throws PathReadException, IncorrectFileException {
        // Считываем модель
        Model model = getModel(filepath);

        Vector3f vector3f = new Vector3f(1.5F,1.0F, 0.8F);
        List<Vector3f> newVertexes = scale(model.vertices, vector3f);

        Vector3f expectedVector = new Vector3f(model.vertices.get(0));
        expectedVector.x *= vector3f.x;
        expectedVector.y *= vector3f.y;
        expectedVector.z *= vector3f.z;
        // проверяем ожидаемую вершину с погрешностью
        assertTrue(expectedVector.epsilonEquals(newVertexes.get(0), 1E-4f));
    }

    @org.junit.jupiter.api.Test
    void testTranslateObject()throws PathReadException, IncorrectFileException {
        // Считываем модель
        Model model = getModel(filepath);
        // сдвиг
        Vector3f translation = new Vector3f(2.0f,4.1f,5.6f);
        // ожидаемый вектор
        Vector3f expectedVector = new Vector3f(model.vertices.get(0));
        expectedVector.x += translation.x;
        expectedVector.y += translation.y;
        expectedVector.z += translation.z;

        List<Vector3f> newVertexes = translate(model.vertices,translation);
        // проверяем ожидаемую вершину с погрешностью
        assertTrue(expectedVector.epsilonEquals(newVertexes.get(0), 1E-4f));

    }

    public static Model getModel(String filepath) throws IncorrectFileException, PathReadException {
        String fileData = null;
        try {
            Path fileName = Path.of(filepath);
            fileData = Files.readString(fileName);
        } catch (IOException e) {
            throw new PathReadException();
        }
        return ObjReader.read(fileData);
    }
}