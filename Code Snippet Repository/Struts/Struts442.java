    private String readContents(URL resource) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (InputStream reader = new BufferedInputStream(resource.openStream())) {
            int b = reader.read();
            while (b != -1) {
                sb.append((char) b);
                b = reader.read();
            }

            return sb.toString().trim();
        }
    }
