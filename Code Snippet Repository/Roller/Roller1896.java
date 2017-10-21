    public List<ThemeTemplate> getTemplates() throws WebloggerException {
        
        Map<String, ThemeTemplate> pages = new TreeMap<String, ThemeTemplate>();
        
        // first get the pages from the db
        try {
            for (ThemeTemplate template : WebloggerFactory.getWeblogger().getWeblogManager().getTemplates(this.weblog)) {
                pages.put(template.getName(), template);
            }
        } catch(Exception e) {
            // db error
            log.error(e);
        }
        
        
        // now get theme pages if needed and put them in place of db pages
        try {
            for (ThemeTemplate template : this.theme.getTemplates()) {
                // note that this will put theme pages over custom
                // pages in the pages list, which is what we want
                pages.put(template.getName(), template);
            }
        } catch(Exception e) {
            // how??
            log.error(e);
        }
        
        return new ArrayList<ThemeTemplate>(pages.values());
    }
