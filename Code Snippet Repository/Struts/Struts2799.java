    private boolean popFile() throws JasperException {

        // Is stack created ? (will happen if the Jsp file we're looking at is
        // missing.
        if (current == null || currFileId < 0) {
            return false;
        }

        // Restore parser state:
        String fName = getFile(currFileId);
        currFileId = unregisterSourceFile(fName);
        if (currFileId < -1) {
            err.jspError("jsp.error.file.not.registered", fName);
        }

        Mark previous = current.popStream();
        if (previous != null) {
            master = current.baseDir;
            current = previous;
            return true;
        }
        // Note that although the current file is undefined here, "current"
        // is not set to null just for convience, for it maybe used to
        // set the current (undefined) position.
        return false;
    }
