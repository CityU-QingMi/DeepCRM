    public boolean isEntryURI(AtomRequest areq) {
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        if (pathInfo.length > 2 && pathInfo[1].equals("entry")) {
            return true;
        }
        if (pathInfo.length > 2 && pathInfo[1].equals("resource") && pathInfo[pathInfo.length-1].endsWith(".media-link")) {
            return true;
        }
        return false;
    }
