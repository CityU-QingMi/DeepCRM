    public static WeblogCategory setupWeblogCategory(Weblog weblog, String name)
            throws Exception {

        WeblogEntryManager mgr = WebloggerFactory.getWeblogger()
                .getWeblogEntryManager();

        WeblogCategory testCat = new WeblogCategory(weblog, name, null, null);
        mgr.saveWeblogCategory(testCat);

        // flush to db
        WebloggerFactory.getWeblogger().flush();

        // query for object
        WeblogCategory cat = mgr.getWeblogCategory(testCat.getId());

        if (cat == null) {
            throw new WebloggerException("error setting up weblog category");
        }

        return cat;
    }
