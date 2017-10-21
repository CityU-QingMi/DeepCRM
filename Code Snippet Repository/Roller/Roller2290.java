    public static WeblogBookmarkFolder setupFolder(Weblog weblog, String name)
            throws Exception {

        BookmarkManager mgr = WebloggerFactory.getWeblogger()
                .getBookmarkManager();

        WeblogBookmarkFolder testFolder = new WeblogBookmarkFolder(name, weblog);
        mgr.saveFolder(testFolder);

        // flush to db
        WebloggerFactory.getWeblogger().flush();

        // query to make sure we return the persisted object
        testFolder = mgr.getFolder(testFolder.getId());

        return testFolder;
    }
