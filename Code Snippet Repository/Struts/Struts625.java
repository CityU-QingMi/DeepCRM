    private InputStream readProperty(String propName) {
        InputStream is = tryReadingPropertyFileFromFileSystem(propName);
        if (is == null) {
            is = readPropertyFromClasspath(propName);
        }
        if (is == null) {
            is = readPropertyUsingServletContext(propName);
        }
        return is;
    }
