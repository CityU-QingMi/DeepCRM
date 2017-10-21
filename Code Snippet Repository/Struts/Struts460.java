    public URL normalizeToFileProtocol(URL url) {
        String fileName = url.toExternalForm();
        Matcher jarMatcher = JAR_PATTERN.matcher(fileName);
        try {
            if (jarMatcher.matches()) {
                String path = jarMatcher.group(JAR_FILE_PATH);
                return new URL("file", "", path);
            } else if ("file".equals(url.getProtocol())) {
                return url; // it's already a file
            } else {
                LOG.warn("Could not normalize URL [{}] to file protocol!", url);
                return null;
            }
        } catch (MalformedURLException e) {
            LOG.warn("Error normalizing URL [{}] to file protocol!", url, e);
            return null;
        }
    }
