    public URI getURI() {
        URI sourceURI = null;
        if (url != null) {
            try {
                sourceURI = url.toURI();
            } catch (final URISyntaxException ex) {
                    /* Ignore the exception */
            }
        }
        if (sourceURI == null && file != null) {
            sourceURI = file.toURI();
        }
        if (sourceURI == null && location != null) {
            try {
                sourceURI = new URI(location);
            } catch (final URISyntaxException ex) {
                // Assume the scheme was missing.
                try {
                    sourceURI = new URI("file://" + location);
                } catch (final URISyntaxException uriEx) {
                    /* Ignore the exception */
                }
            }
        }
        return sourceURI;
    }
