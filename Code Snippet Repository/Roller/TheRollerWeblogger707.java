    public String execute() {
        
        if (log.isDebugEnabled()) {
            log.debug("entries bean is ...\n"+getBean().toString());
        }
        
        List<WeblogEntry> entries = null;
        boolean hasMore = false;
        try {
            String status = getBean().getStatus();
            
            WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
            WeblogEntrySearchCriteria wesc = new WeblogEntrySearchCriteria();
            wesc.setWeblog(getActionWeblog());
            wesc.setStartDate(getBean().getStartDate());
            wesc.setEndDate(getBean().getEndDate());
            wesc.setCatName(getBean().getCategoryName());
            wesc.setTags(getBean().getTags());
            wesc.setStatus("ALL".equals(status) ? null : WeblogEntry.PubStatus.valueOf(status));
            wesc.setText(getBean().getText());
            wesc.setSortBy(getBean().getSortBy());
            wesc.setOffset(getBean().getPage() * COUNT);
            wesc.setMaxResults(COUNT + 1);
            List<WeblogEntry> rawEntries = wmgr.getWeblogEntries(wesc);
            entries = new ArrayList<WeblogEntry>();
            entries.addAll(rawEntries);
            if (entries.size() > 0) {
                log.debug("query found "+rawEntries.size()+" results");
                
                if(rawEntries.size() > COUNT) {
                    entries.remove(entries.size()-1);
                    hasMore = true;
                }
                
                setFirstEntry(entries.get(0));
                setLastEntry(entries.get(entries.size()-1));
            }
        } catch (WebloggerException ex) {
            log.error("Error looking up entries", ex);
            addError("Error looking up entries");
        }
        
        // build entries pager
        String baseUrl = buildBaseUrl();
        setPager(new EntriesPager(baseUrl, getBean().getPage(), entries, hasMore));
                
        return LIST;
    }
