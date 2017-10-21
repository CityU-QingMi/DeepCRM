    public void saveWeblogCategory(WeblogCategory cat) throws WebloggerException {
        boolean exists = getWeblogCategory(cat.getId()) != null;
        if (!exists && isDuplicateWeblogCategoryName(cat)) {
            throw new WebloggerException("Duplicate category name, cannot save category");
        }

        // update weblog last modified date.  date updated by saveWebsite()
        roller.getWeblogManager().saveWeblog(cat.getWeblog());
        this.strategy.store(cat);
    }
