    public String findString(String uri) throws IOException {
        String fullUri = path + uri;

        URL resource = getResource(fullUri);
        if (resource == null) {
            throw new IOException("Could not find a resource in: " + fullUri);
        }

        return readContents(resource);
    }
