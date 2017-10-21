    public List getMostCommentedWeblogs(int sinceDays , int length) {
        List results = new ArrayList();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1 * sinceDays);
        Date startDate = cal.getTime();
        try {            
            results = WebloggerFactory.getWeblogger().getWeblogManager().getMostCommentedWeblogs(
                    startDate, new Date(), 0, length);
        } catch (Exception e) {
            log.error("ERROR: fetching commented weblog list", e);
        }
        return results;
    }
