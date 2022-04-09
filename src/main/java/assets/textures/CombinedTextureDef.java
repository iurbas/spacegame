package assets.textures;

public class CombinedTextureDef {
    
    private String diffuse;
    private String normal;
    private String specular;
    
    public CombinedTextureDef(String diffuse, String normal, String specular) {
        this.diffuse = diffuse;
        this.normal = normal;
        this.specular = specular;
    }
    
    public CombinedTextureDef(String diffuse, String normal) {
        this(diffuse, normal, null);
    }
    
    public CombinedTextureDef(String diffuse) {
        this(diffuse, null, null);
    }
    
    public String getDiffuse() {
        return diffuse;
    }
    
    public String getNormal() {
        return normal;
    }
    
    public String getSpecular() {
        return specular;
    }
    
}
