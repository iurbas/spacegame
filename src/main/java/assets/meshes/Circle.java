package assets.meshes;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import java.nio.FloatBuffer;

public class Circle extends Mesh {
    
    private Vector3f center;
    private float radius;
    private int samples;
    
    public Circle(float radius) {
        this(Vector3f.ZERO, radius, 32);
    }
    
    public Circle(float radius, int samples) {
        this(Vector3f.ZERO, radius, samples);
    }
    
    public Circle(Vector3f center, float radius, int samples) {
        this.center = center;
        this.radius = radius;
        this.samples = samples;
        setMode(Mode.Lines);
        updateGeometry();
    }
    
    private void updateGeometry() {
        FloatBuffer positions = BufferUtils.createFloatBuffer(samples * 3);
        FloatBuffer normals = BufferUtils.createFloatBuffer(samples * 3);
        int[] indices = new int[samples * 2];
        float rate = FastMath.TWO_PI / (float) samples;
        float angle = 0;
        int currentIndice = 0;
        for(int i = 0; i < samples; i++)
        {
            float x = FastMath.cos(angle)*radius + center.x;
            float z = FastMath.sin(angle)*radius + center.z;
            positions.put(x).put(center.y).put(z);
            normals.put(new float[] { 0, 0, 1 });
            indices[currentIndice] = i;
            currentIndice++;
            indices[currentIndice] = (i < samples - 1) ? (i + 1) : 0;
            currentIndice++;
            angle += rate;
        }
        setBuffer(Type.Position, 3, positions);
        setBuffer(Type.Normal, 3, normals);
        setBuffer(Type.Index, 2, indices);
        setBuffer(Type.TexCoord, 2, new float[] { 0, 0, 1, 1 });
        updateBound();
    }
   
}
