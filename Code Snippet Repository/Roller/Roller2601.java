    public FileContent getFileContent(Weblog weblog, String fileId)
            throws FileNotFoundException, FilePathException {

        // get a reference to the file, checks that file exists & is readable
        File resourceFile = this.getRealFile(weblog, fileId);

        // make sure file is not a directory
        if (resourceFile.isDirectory()) {
            throw new FilePathException("Invalid file id [" + fileId + "], "
                    + "path is a directory.");
        }

        // everything looks good, return resource
        return new FileContent(weblog, fileId, resourceFile);
    }
