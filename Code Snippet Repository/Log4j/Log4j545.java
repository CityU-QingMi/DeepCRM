    private String createClassPath(final Class<?>... classes) throws Exception {
        final StringBuilder result = new StringBuilder();
        for (final Class<?> cls : classes) {
            if (result.length() > 0) {
                result.append(File.pathSeparator);
            }
            result.append(createClassPath(cls));
        }
        return result.toString();
    }
