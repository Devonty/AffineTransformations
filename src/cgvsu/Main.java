package cgvsu;

import cgvsu.AffineTransformation.Transformation.RotateXOperator;
import cgvsu.AffineTransformation.Transformation.ScaleOperator;
import cgvsu.AffineTransformation.Transformation.TranslateOperator;
import cgvsu.AffineTransformation.Transformer;
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
        test2();
    }

    public static void test2() throws IncorrectFileException {
        String fileData = null;
        try {
            Path fileName = Path.of("Objects\\WrapHead.obj");
            fileData = Files.readString(fileName);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        Model model = ObjReader.read(fileData);

        Transformer transformer = new Transformer();
        transformer.addTransformation(new ScaleOperator(2f, 1f, 1.5f));
        transformer.addTransformation(new RotateXOperator((float) Math.PI));
        transformer.addTransformation(new TranslateOperator(new Vector3f(100f, 200f, 0f)));
        transformer.addTransformation(new ScaleOperator(2f, 1f, 1.5f));

        //Copy model.vertices
        transformer.transform(model.vertices);

        ObjWriter.writeModelToObjFile("Objects\\saved.obj",model);
    }
}
