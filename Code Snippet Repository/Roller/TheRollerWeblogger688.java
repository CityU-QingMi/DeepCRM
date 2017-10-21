    public String execute() {

        // build list of folders that the user can navigate to
        List<WeblogBookmarkFolder> newFolders = new ArrayList<WeblogBookmarkFolder>();

        try {
            // Build list of all folders, except for current one
            BookmarkManager bmgr = WebloggerFactory.getWeblogger().getBookmarkManager();
            List<WeblogBookmarkFolder> folders = bmgr.getAllFolders(getActionWeblog());
            for (WeblogBookmarkFolder fd : folders) {
                if (!fd.getId().equals(getFolderId())) {
                    newFolders.add(fd);
                }
            }

        } catch (WebloggerException ex) {
            log.error("Error building folders list", ex);
            addError("Error building folders list");
        }

        if (newFolders.size() > 0) {
            setAllFolders(newFolders);
        }

        return LIST;
    }
