    private final void setPath(String path)
    throws LockFile.FileCanonicalizationException,
           LockFile.FileSecurityException {

        // Should at least be absolutized for reporting purposes, just in case
        // a security or canonicalization exception gets thrown.
        path      = FileUtil.getFileUtil().canonicalOrAbsolutePath(path);
        this.file = new File(path);

        try {
            FileUtil.getFileUtil().makeParentDirectories(this.file);
        } catch (SecurityException ex) {
            throw new FileSecurityException(this, "setPath", ex);
        }

        try {
            this.file = FileUtil.getFileUtil().canonicalFile(path);
        } catch (SecurityException ex) {
            throw new FileSecurityException(this, "setPath", ex);
        } catch (IOException ex) {
            throw new FileCanonicalizationException(this, "setPath", ex);
        }

        this.cpath = this.file.getPath();
    }
