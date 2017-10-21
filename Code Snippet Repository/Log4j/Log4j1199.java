    private String readContents(final InputStream in, final Charset charset) throws IOException {
        Reader reader = null;
        try {
            reader = new InputStreamReader(in, charset);
            final StringBuilder result = new StringBuilder(TEXT_BUFFER);
            final char[] buff = new char[PAGE];
            int count = -1;
            while ((count = reader.read(buff)) >= 0) {
                result.append(buff, 0, count);
            }
            return result.toString();
        } finally {
            Closer.closeSilently(in);
            Closer.closeSilently(reader);
        }
    }
