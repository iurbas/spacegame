package assets.textures;

public class TiledTextureDef {
    
    private String texture;
    private int imagesX;
    private int imagesY;
    
    public TiledTextureDef(String texture, int imagesX, int imagesY) {
        this.texture = texture;
        this.imagesX = imagesX;
        this.imagesY = imagesY;
    }
    
    public String getTexture() {
        return texture;
    }
    
    public int getImagesX() {
        return imagesX;
    }
    
    public int getImagesY() {
        return imagesY;
    }
    
}
