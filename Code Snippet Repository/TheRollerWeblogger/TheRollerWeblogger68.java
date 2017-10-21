    public static void teardownFolder(String id) throws Exception {

        // lookup the folder
        BookmarkManager mgr = WebloggerFactory.getWeblogger()
                .getBookmarkManager();
        WeblogBookmarkFolder folder = mgr.getFolder(id);

        // remove the cat
        mgr.removeFolder(folder);

        // flush to db
        WebloggerFactory.getWeblogger().flush();
    }
