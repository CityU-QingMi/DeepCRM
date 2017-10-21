    public List<WeblogEntryWrapper> getItems() {
        
        if (entries == null) {
            // calculate offset
            int offset = getPage() * length;

            List<WeblogEntryWrapper> results = new ArrayList<WeblogEntryWrapper>();
            
            Date startDate = null;
            if(sinceDays > 0) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DATE, -1 * sinceDays);
                startDate = cal.getTime();
            }
            
            try {
                WeblogEntrySearchCriteria wesc = new WeblogEntrySearchCriteria();
                wesc.setWeblog(queryWeblog);
                wesc.setUser(queryUser);
                wesc.setStartDate(startDate);
                wesc.setCatName(queryCat);
                wesc.setTags(queryTags);
                wesc.setStatus(WeblogEntry.PubStatus.PUBLISHED);
                wesc.setLocale(locale);
                wesc.setOffset(offset);
                wesc.setMaxResults(length+1);
                List<WeblogEntry> rawEntries = WebloggerFactory.getWeblogger()
                        .getWeblogEntryManager().getWeblogEntries(wesc);

                // wrap the results
                int count = 0;
                for (WeblogEntry entry : rawEntries) {
                    if (count++ < length) {
                        results.add(WeblogEntryWrapper.wrap(entry, urlStrategy));
                    }
                }
                if (rawEntries.size() > length) {
                    more = true;
                }
                
            } catch (Exception e) {
                log.error("ERROR: fetching weblog entries list", e);
            }
            
            entries = results;
        }
        
        return entries;
    }
