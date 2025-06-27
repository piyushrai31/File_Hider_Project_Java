package Model;

public class Data {
    private int id;
    private String fileName;
    private String path;
    private String email;

    public Data(String email, String path, String fileName, int id) {
        this.email = email;
        this.path = path;
        this.fileName = fileName;
        this.id = id;
    }

    public Data(int id, String fileName, String path) {
        this.id = id;
        this.fileName = fileName;
        this.path = path;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
