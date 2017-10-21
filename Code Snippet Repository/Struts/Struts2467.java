    private List<String> jar(URL location) throws IOException {
        URL url = fileManager.normalizeToFileProtocol(location);
        if (url != null) {
            InputStream in = url.openStream();
            try {
                JarInputStream jarStream = new JarInputStream(in);
                return jar(jarStream);
            } finally {
                in.close();
            }
        } else {
            LOG.debug("Unable to read [{}]", location.toExternalForm());
        }
        return Collections.emptyList();
    }
