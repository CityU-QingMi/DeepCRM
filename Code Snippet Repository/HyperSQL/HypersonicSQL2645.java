    public String getSecurePath(String path, boolean allowFull,
                                boolean includeRes) {

        if (database.getType() == DatabaseType.DB_RES) {
            if (includeRes) {
                return path;
            } else {
                return null;
            }
        }

        if (database.getType() == DatabaseType.DB_MEM) {
            if (propTextAllowFullPath) {
                return path;
            } else {
                return null;
            }
        }

        // absolute paths
        if (path.startsWith("/") || path.startsWith("\\")
                || path.indexOf(":") > -1) {
            if (allowFull || propTextAllowFullPath) {
                return path;
            } else {
                return null;
            }
        }

        if (path.indexOf("..") > -1) {
            if (allowFull || propTextAllowFullPath) {

                // allow
            } else {
                return null;
            }
        }

        String fullPath =
            new File(new File(database.getPath()
                              + ".properties").getAbsolutePath()).getParent();

        if (fullPath != null) {
            path = fullPath + File.separator + path;
        }

        return path;
    }
