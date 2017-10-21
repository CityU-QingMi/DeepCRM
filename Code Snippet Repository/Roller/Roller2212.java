    public static void teardownComment(String id) throws Exception {

        // lookup the comment
        WeblogEntryManager mgr = WebloggerFactory.getWeblogger()
                .getWeblogEntryManager();
        WeblogEntryComment comment = mgr.getComment(id);

        // remove the comment
        mgr.removeComment(comment);

        // flush to db
        WebloggerFactory.getWeblogger().flush();
    }
