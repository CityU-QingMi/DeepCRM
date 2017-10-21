    private PrintStream parseStreamName(final String name) throws URISyntaxException, FileNotFoundException {
        if (name == null || name.equalsIgnoreCase("out")) {
            return DEFAULT_STREAM;
        }
        if (name.equalsIgnoreCase("err")) {
            return System.err;
        }
        final URI destUri = NetUtils.toURI(name);
        final File output = FileUtils.fileFromUri(destUri);
        if (output == null) {
            // don't want any NPEs, no sir
            return DEFAULT_STREAM;
        }
        final FileOutputStream fos = new FileOutputStream(output);
        return new PrintStream(fos, true);
    }
