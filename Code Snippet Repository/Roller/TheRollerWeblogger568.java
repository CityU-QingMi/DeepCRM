    public Map<Date, List<WeblogEntryWrapper>> getEntries() {
        Date date = parseDate(dateString);
        Calendar cal = Calendar.getInstance(weblog.getTimeZoneInstance());
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        date = cal.getTime();
        Date startDate = DateUtil.getStartOfMonth(date, cal);
        Date endDate = DateUtil.getEndOfMonth(date, cal);
        
        if (entries == null) {
            entries = new TreeMap<Date, List<WeblogEntryWrapper>>(Collections.reverseOrder());
            try {
                WeblogEntrySearchCriteria wesc = new WeblogEntrySearchCriteria();
                wesc.setWeblog(weblog);
                wesc.setStartDate(startDate);
                wesc.setEndDate(endDate);
                wesc.setCatName(catName);
                wesc.setTags(tags);
                wesc.setStatus(WeblogEntry.PubStatus.PUBLISHED);
                wesc.setLocale(locale);
                wesc.setOffset(offset);
                wesc.setMaxResults(length+1);
                Map<Date, List<WeblogEntry>> mmap = WebloggerFactory.getWeblogger()
                        .getWeblogEntryManager().getWeblogEntryObjectMap(wesc);

                // need to wrap pojos
                int count = 0;
                for (Map.Entry<Date, List<WeblogEntry>> entry : mmap.entrySet()) {
                    // now we need to go through each entry in a day and wrap
                    List<WeblogEntryWrapper> wrapped = new ArrayList<WeblogEntryWrapper>();
                    List<WeblogEntry> unwrapped = entry.getValue();
                    for (int i=0; i < unwrapped.size(); i++) {
                        if (count++ < length) {
                            wrapped.add(i,WeblogEntryWrapper.wrap(unwrapped.get(i), urlStrategy));
                        } else {
                            more = true;
                        }
                    }
                    
                    // done with that day, put it in the map
                    if (wrapped.size() > 0) {
                        entries.put(entry.getKey(), wrapped);
                    }
                }
            } catch (Exception e) {
                log.error("ERROR: getting entry month map", e);
            }
        }
        return entries;
    }
