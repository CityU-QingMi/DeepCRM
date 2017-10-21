    public void myPrepare() {
        if (StringUtils.isEmpty(bean.getId())) {
            // Create and initialize new folder but don't save yet
            folder = new WeblogBookmarkFolder();
            folder.setWeblog(getActionWeblog());
        } else {
            // retrieve existing folder data from DB
            try {
                BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();
                folder = bmgr.getFolder(getBean().getId());
            } catch (WebloggerException ex) {
                log.error("Error looking up folder", ex);
            }
        }
    }
