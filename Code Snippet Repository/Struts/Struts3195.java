    public void start(String name, Attributes attrs) throws IOException {
        writer.write("<");
        writer.write(name);
        if (attrs != null) {
            for (String key : attrs.keySet()) {
                writer.write(" ");
                writer.write(key);
                writer.write("=\"");
                writer.write(attrs.get(key));
                writer.write("\"");
            }
        }

        writer.write(">");
    }
