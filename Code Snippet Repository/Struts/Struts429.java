    public Properties findProperties(String uri) throws IOException {
        String fulluri = path + uri;

        URL resource = getResource(fulluri);
        if (resource == null) {
            throw new IOException("Could not find command in : " + fulluri);
        }

        return loadProperties(resource);
    }
