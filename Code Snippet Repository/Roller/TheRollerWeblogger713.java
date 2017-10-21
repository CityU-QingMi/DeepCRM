    public void copyTo(WeblogEntry entry) throws WebloggerException {
        
        entry.setTitle(getTitle());
        entry.setStatus(PubStatus.valueOf(getStatus()));
        entry.setLocale(getLocale());
        entry.setSummary(getSummary());
        entry.setText(getText());
        entry.setTagsAsString(getTagsAsString());
        entry.setSearchDescription(getSearchDescription());
        
        // figure out the category selected
        if (getCategoryId() != null) {
            WeblogCategory cat = null;
            try {
                WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
                cat = wmgr.getWeblogCategory(getCategoryId());
            } catch (WebloggerException ex) {
                log.error("Error getting category by id", ex);
            }
            
            if(cat == null) {
                throw new WebloggerException("Category could not be found - "+getCategoryId());
            } else if(!entry.getWebsite().equals(cat.getWeblog())) {
                throw new WebloggerException("Illegal category, not owned by action weblog");
            } else {
                entry.setCategory(cat);
            }
        } else {
            throw new WebloggerException("No category specified");
        }
        
        // join values from all plugins into a single string
        entry.setPlugins(StringUtils.join(getPlugins(),","));
        
        // comment settings & right-to-left option
        entry.setAllowComments(getAllowComments());
        entry.setCommentDays(getCommentDays());
        entry.setRightToLeft(getRightToLeft());
        
        // NOTE: pubtime and pinned to main attributes are set in action
    }
