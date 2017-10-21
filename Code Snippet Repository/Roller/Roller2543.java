    public boolean isCollectionURI(AtomRequest areq) {
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        if (pathInfo.length > 1 && pathInfo[1].equals("entries")) {
            return true;
        }
        if (pathInfo.length > 1 && pathInfo[1].equals("resources")) {
            return true;
        }
        if (pathInfo.length > 1 && pathInfo[1].equals("categories")) {
            return true;
        }
        return false;
    }
