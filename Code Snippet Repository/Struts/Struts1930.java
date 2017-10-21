    protected String readAsString(String resource) throws Exception {
        try (InputStream is = ClassLoaderUtil.getResourceAsStream(resource, PlainTextResultTest.class)) {
            int sizeRead = 0;
            byte[] buffer = new byte[1024];
            StringBuilder stringBuilder = new StringBuilder();
            while((sizeRead = is.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer, 0, sizeRead));
            }
            return stringBuilder.toString();
        }
    }
