    private String filePathFromPathInfo(String[] pathInfo) {
        String path = null;
        if (pathInfo.length > 2) {
            for (int i = 2; i < pathInfo.length; i++) {
                if (path != null && path.length() > 0) {
                    path = path + File.separator + pathInfo[i];
                }
                else {
                    path = pathInfo[i];
                }
            }
        } if (pathInfo.length == 2) {
            path = "";
        }
        return path;
    }
