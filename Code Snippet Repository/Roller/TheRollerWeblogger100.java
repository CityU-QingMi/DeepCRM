    private File getRealFile(Weblog weblog, String fileId)
            throws FileNotFoundException, FilePathException {

        // make sure uploads area exists for this weblog
        File weblogDir = new File(this.storageDir + weblog.getHandle());
        if (!weblogDir.exists()) {
            weblogDir.mkdirs();
        }

        // now form the absolute path
        String filePath = weblogDir.getAbsolutePath();
        if (fileId != null) {
            filePath += File.separator + fileId;
        }

        // make sure path exists and is readable
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Invalid path [" + filePath + "], "
                    + "file does not exist.");
        } else if (!file.canRead()) {
            throw new FilePathException("Invalid path [" + filePath + "], "
                    + "cannot read from path.");
        }

        try {
            // make sure someone isn't trying to sneek outside the uploads dir
            if (!file.getCanonicalPath().startsWith(
                    weblogDir.getCanonicalPath())) {
                throw new FilePathException("Invalid path " + filePath + "], "
                        + "trying to get outside uploads dir.");
            }
        } catch (IOException ex) {
            // rethrow as FilePathException
            throw new FilePathException(ex);
        }

        return file;
    }
