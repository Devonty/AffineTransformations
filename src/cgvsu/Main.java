package cgvsu;

import cgvsu.AffineTransformation.AffineTransformations;
import cgvsu.model.Model;
import cgvsu.objreader.IncorrectFileException;
import cgvsu.objreader.ObjReader;
import cgvsu.objreader.PathReadException;
import cgvsu.objwriter.ObjWriter;

import javax.vecmath.Vector3f;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IncorrectFileException {
        String fileData = null;
        try {
            Path fileName = Path.of("Objects\\WrapHead.obj");
            fileData = Files.readString(fileName);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        Model model = ObjReader.read(fileData);
        Vector3f scales = new Vector3f(10f, 10f, 1f);
        Vector3f rotation = new Vector3f((float) Math.PI, (float) Math.PI/2f, 0f);
        Vector3f transportation = new Vector3f(400f, 200f, 0f);

        ArrayList<Vector3f> newVertices = AffineTransformations.scaleRotateTranslate(
                model.vertices,
                scales,
                rotation,
                transportation
        );

        Model toSave = new Model(newVertices, model.textureVertices, model.normals, model.polygons);
        ObjWriter.writeModelToObjFile("Objects\\saved.obj", toSave);
    }
}
