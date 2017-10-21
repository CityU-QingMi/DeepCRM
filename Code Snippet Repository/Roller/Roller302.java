    public void removeBookmark(WeblogBookmark bookmark) throws WebloggerException {
        Weblog weblog = bookmark.getWebsite();
        
        //Remove the bookmark from its parent folder
        bookmark.getFolder().getBookmarks().remove(bookmark);
        
        // Now remove it from database
        this.strategy.remove(bookmark);
        
        // update weblog last modified date.  date updated by saveWebsite()
        roller.getWeblogManager().saveWeblog(weblog);
    }
