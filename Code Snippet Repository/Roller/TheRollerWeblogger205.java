    public void removeWeblogCategory(WeblogCategory cat)
    throws WebloggerException {
        if(cat.retrieveWeblogEntries(false).size() > 0) {
            throw new WebloggerException("Cannot remove category with entries");
        }

        cat.getWeblog().getWeblogCategories().remove(cat);

        // remove cat
        this.strategy.remove(cat);

        if(cat.equals(cat.getWeblog().getBloggerCategory())) {
            cat.getWeblog().setBloggerCategory(null);
            this.strategy.store(cat.getWeblog());
        }

        // update weblog last modified date.  date updated by saveWebsite()
        roller.getWeblogManager().saveWeblog(cat.getWeblog());
    }
