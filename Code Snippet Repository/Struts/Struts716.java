    protected String getCanonicalName(final String originalFileName) {
        String fileName = originalFileName;

        int forwardSlash = fileName.lastIndexOf("/");
        int backwardSlash = fileName.lastIndexOf("\\");
        if (forwardSlash != -1 && forwardSlash > backwardSlash) {
            fileName = fileName.substring(forwardSlash + 1, fileName.length());
        } else {
            fileName = fileName.substring(backwardSlash + 1, fileName.length());
        }
        return fileName;
    }
