    public void writeTo(JspWriter out, String encoding) throws IOException {
        try {
            writeTo((Writer) out, encoding);
        } catch (IOException e) {
            writeToFile();
            throw e;
        } catch (Throwable e) {
            writeToFile();
            throw new RuntimeException(e);
        }
    }
