    private File toCanonicalOrAbsoluteFile(String path) {
        File file = new File(path);

        if (!file.isAbsolute()) {
            path = (new File(this.documentPath)).getParent()
                    + File.separatorChar
                    + path;

            file = new File(path);
        }

        try {
            return file.getCanonicalFile();
        } catch (Exception e) {
            return file.getAbsoluteFile();
        }
    }
