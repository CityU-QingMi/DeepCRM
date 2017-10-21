    public void save() throws Exception {

        if (fileName == null || fileName.length() == 0) {
            throw new java.io.FileNotFoundException(
                Error.getMessage(ErrorCode.M_HsqlProperties_load));
        }

        String filestring = fileName + fileExtension;

        save(filestring);
    }
