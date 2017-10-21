    protected String getSystemIdForFileName(String fileName) {
        File file = new File(fileName); // files in filesystem
        if (file.exists()) {
            try {
                new FileInputStream(file).close();
                return file.toURI().toString();
            }catch (IOException ignore) {
                return fileName;
            }
        } else {
            URL url = getURL(fileName);
            if (url == null) {
                return fileName;
            } else {
                try {
                    url.openStream().close();
                    return url.toString();
                } catch (IOException ignore) {
                    return fileName;
                }
            }      
        }
    }
