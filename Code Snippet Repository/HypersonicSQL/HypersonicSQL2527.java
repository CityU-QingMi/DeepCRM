    public boolean load() throws Exception {

        if (fileName == null || fileName.length() == 0) {
            throw new FileNotFoundException(
                Error.getMessage(ErrorCode.M_HsqlProperties_load));
        }

        if (!propertiesFileExists()) {
            return false;
        }

        InputStream fis           = null;
        String      propsFilename = fileName + fileExtension;

// oj@openoffice.org
        try {
            fis = fa.openInputStreamElement(propsFilename);

            stringProps.load(fis);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

        return true;
    }
