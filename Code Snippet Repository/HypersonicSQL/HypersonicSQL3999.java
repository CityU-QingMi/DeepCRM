    private String resolvePath(String path) {
        if (path == null) {
            throw new IllegalArgumentException("path: null");
        }

        String value = this.resolver.resolveProperties(path);
        File   file  = this.resolver.resolveFile(value);
        
        try {
            return file.getCanonicalPath();
        } catch (IOException ex) {
            return file.getAbsolutePath();
        }
    }
