    protected String createURL(
            int                page,
            int                pageAdd,
            Weblog        website,
            String             locale,
            String             pageLink,
            String             entryAnchor,
            String             dateString,
            String             catName,
            List               tags) {
        
        int pageNum = page + pageAdd;
        
        if (pageLink != null) {
            return urlStrategy.getWeblogPageURL(website, locale, pageLink, entryAnchor, catName, dateString, tags, pageNum, false);
        } else if (entryAnchor != null) {
            return urlStrategy.getWeblogEntryURL(website, locale, entryAnchor, true);
        }
        
        return urlStrategy.getWeblogCollectionURL(website, locale, catName, dateString, tags, pageNum, false);
    }
