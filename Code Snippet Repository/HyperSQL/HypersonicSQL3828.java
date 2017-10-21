    public CSVWriter(File file, String encoding) throws IOException {

        if (encoding == null) {
            encoding = System.getProperty("file.encoding");
        }

        FileOutputStream fout = new FileOutputStream(file);

        writer = new OutputStreamWriter(fout, encoding);
    }
