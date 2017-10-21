    public List<WeblogWrapper> getItems() {
        
        if (weblogs == null) {
            // calculate offset
            int offset = getPage() * length;
            
            List<WeblogWrapper> results = new ArrayList<WeblogWrapper>();
            Date startDate = null;
            if (sinceDays != -1) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DATE, -1 * sinceDays);
                startDate = cal.getTime();
            }
            try {
                Weblogger roller = WebloggerFactory.getWeblogger();
                WeblogManager wmgr = roller.getWeblogManager();
                List<Weblog> rawWeblogs;
                if (letter == null) {
                    rawWeblogs = wmgr.getWeblogs(Boolean.TRUE, Boolean.TRUE, startDate, null, offset, length + 1);
                } else {
                    rawWeblogs = wmgr.getWeblogsByLetter(letter.charAt(0), offset, length + 1);
                }
                
                // wrap the results
                int count = 0;
                for (Weblog website : rawWeblogs) {
                    if (count++ < length) {
                        results.add(WeblogWrapper.wrap(website, urlStrategy));
                    } else {
                        more = true;
                    }
                }
                
            } catch (Exception e) {
                log.error("ERROR: fetching weblog list", e);
            }
            
            weblogs = results;
        }
        
        return weblogs;
    }
