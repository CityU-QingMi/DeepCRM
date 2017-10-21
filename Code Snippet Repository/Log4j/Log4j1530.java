    public static File fileFromUri(URI uri) {
        // There MUST be a better way to do this. TODO Search other ASL projects...
        if (uri == null || (uri.getScheme() != null
                && (!PROTOCOL_FILE.equals(uri.getScheme()) && !JBOSS_FILE.equals(uri.getScheme())))) {
            return null;
        }
        if (uri.getScheme() == null) {
            File file = new File(uri.toString());
            if (file.exists()) {
                return file;
            }
            try {
                final String path = uri.getPath();
                file = new File(path);
                if (file.exists()) {
                    return file;
                }
                uri = new File(path).toURI();
            } catch (final Exception ex) {
                LOGGER.warn("Invalid URI {}", uri);
                return null;
            }
        }
        final String charsetName = StandardCharsets.UTF_8.name();
        try {
            String fileName = uri.toURL().getFile();
            if (new File(fileName).exists()) { // LOG4J2-466
                return new File(fileName); // allow files with '+' char in name
            }
            fileName = URLDecoder.decode(fileName, charsetName);
            return new File(fileName);
        } catch (final MalformedURLException ex) {
            LOGGER.warn("Invalid URL {}", uri, ex);
        } catch (final UnsupportedEncodingException uee) {
            LOGGER.warn("Invalid encoding: {}", charsetName, uee);
        }
        return null;
    }
