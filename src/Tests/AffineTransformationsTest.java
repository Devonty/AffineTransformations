package Tests;

import cgvsu.AffineTransformation.Transformation.*;
import cgvsu.AffineTransformation.Transformer;
import cgvsu.model.Model;
import cgvsu.objreader.IncorrectFileException;
import cgvsu.objreader.ObjReader;
import cgvsu.objreader.PathReadException;

import javax.vecmath.Vector3f;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AffineTransformationsTest {
    public static final String filepath = "Objects\\WrapHead.obj";
    public static Model model;

    static {
        try {
            model = getModel(filepath);
        } catch (IncorrectFileException e) {
            throw new RuntimeException(e);
        } catch (PathReadException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void testRotateObjectX() throws PathReadException, IncorrectFileException {
        // получем ожидаемую вершину
        Vector3f expectedVector = new Vector3f(model.vertices.get(0));
        expectedVector.z *= -1;
        expectedVector.y *= -1;

        // получаем список новых вершин
        List<Vector3f> newVertexes = model.copyVertexes();
        Transformer transformer = new Transformer();
        transformer.addTransformation(new RotateXOperator((float) Math.PI));
        transformer.transform(newVertexes);
        // проверяем ожидаемую вершину с погрешностью
        assertTrue(expectedVector.epsilonEquals(newVertexes.get(0), 1E-4f));

    }

    @org.junit.jupiter.api.Test
    void testRotateObjectY() throws PathReadException, IncorrectFileException {
        Vector3f expectedVector = new Vector3f(model.vertices.get(0));
        expectedVector.x *= -1;
        expectedVector.z *= -1;

        // получаем список новых вершин
        List<Vector3f> newVertexes = model.copyVertexes();
        Transformer transformer = new Transformer();
        transformer.addTransformation(new RotateYOperator((float) Math.PI));
        transformer.transform(newVertexes);
        // проверяем ожидаемую вершину с погрешностью
        assertTrue(expectedVector.epsilonEquals(newVertexes.get(0), 1E-4f));

    }
    @org.junit.jupiter.api.Test
    void testRotateObjectZ() throws PathReadException, IncorrectFileException {
        Vector3f expectedVector = new Vector3f(model.vertices.get(0));
        expectedVector.x *= -1;
        expectedVector.y *= -1;

        // получаем список новых вершин
        List<Vector3f> newVertexes = model.copyVertexes();
        Transformer transformer = new Transformer();
        transformer.addTransformation(new RotateZOperator((float) Math.PI));
        transformer.transform(newVertexes);
        // проверяем ожидаемую вершину с погрешностью
        assertTrue(expectedVector.epsilonEquals(newVertexes.get(0), 1E-4f));

    }

    @org.junit.jupiter.api.Test
    void testScaleObject()throws PathReadException, IncorrectFileException {
        Vector3f scalesVector = new Vector3f(1.5F,1.0F, 0.8F);

        Vector3f expectedVector = new Vector3f(model.vertices.get(0));
        expectedVector.x *= scalesVector.x;
        expectedVector.y *= scalesVector.y;
        expectedVector.z *= scalesVector.z;

        // получаем список новых вершин
        List<Vector3f> newVertexes = model.copyVertexes();
        Transformer transformer = new Transformer();
        transformer.addTransformation(new ScaleOperator(scalesVector));
        transformer.transform(newVertexes);
        // проверяем ожидаемую вершину с погрешностью
        assertTrue(expectedVector.epsilonEquals(newVertexes.get(0), 1E-4f));
    }
    @org.junit.jupiter.api.Test
    void testTranslateObject()throws PathReadException, IncorrectFileException {
        // сдвиг
        Vector3f translation = new Vector3f(2.0f,4.1f,5.6f);
        // ожидаемый вектор
        Vector3f expectedVector = new Vector3f(model.vertices.get(0));
        expectedVector.x += translation.x;
        expectedVector.y += translation.y;
        expectedVector.z += translation.z;

        // получаем список новых вершин
        List<Vector3f> newVertexes = model.copyVertexes();
        Transformer transformer = new Transformer();
        transformer.addTransformation(new TranslateOperator(translation));
        transformer.transform(newVertexes);
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