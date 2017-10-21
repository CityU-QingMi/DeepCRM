    public List<WeblogWrapper> getNewWeblogs(int sinceDays, int length) {
        List<WeblogWrapper> results = new ArrayList<WeblogWrapper>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1 * sinceDays);
        Date startDate = cal.getTime();
        try {            
            List<Weblog> weblogs = WebloggerFactory.getWeblogger().getWeblogManager().getWeblogs(
                Boolean.TRUE, Boolean.TRUE, startDate, null, 0, length);
            for (Weblog website : weblogs) {
                results.add(WeblogWrapper.wrap(website, urlStrategy));
            }
        } catch (Exception e) {
            log.error("ERROR: fetching weblog list", e);
        }
        return results;
    }
