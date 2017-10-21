    private InputStream getResourceAsStream(String uri)
            throws FileNotFoundException {
        try {
            // see if file exists on the filesystem first
            String real = ctxt.getRealPath(uri);
            if (real == null) {
                return ctxt.getResourceAsStream(uri);
            } else {
                return new FileInputStream(real);
            }
        } catch (FileNotFoundException ex) {
            // if file not found on filesystem, get the resource through
            // the context
            return ctxt.getResourceAsStream(uri);
        }

    }
