    public void saveBookmark(WeblogBookmark bookmark) throws WebloggerException {
        boolean exists = getBookmark(bookmark.getId()) != null;
        if (!exists) {
            // New object make sure that relationship is set on managed copy of other side
            bookmark.getFolder().addBookmark(bookmark);
        }
        // set ranking (order of appearance) of bookmark
        if (bookmark.getPriority() == null) {
            bookmark.calculatePriority();
        }

        this.strategy.store(bookmark);

        // update weblog last modified date (date is updated by saveWebsite())
        roller.getWeblogManager().saveWeblog(bookmark.getWebsite());
    }
