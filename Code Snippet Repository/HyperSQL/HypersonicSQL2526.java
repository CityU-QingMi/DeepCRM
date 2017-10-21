    public boolean propertiesFileExists() {

        if (fileName == null) {
            return false;
        }

        String propFilename = fileName + fileExtension;

        return fa.isStreamElement(propFilename);
    }
