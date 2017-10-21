    public Pager getWeblogsByLetterPager(String letter, int sinceDays, int length) {
        
        String pagerUrl = urlStrategy.getWeblogPageURL(weblog, 
                weblogRequest.getLocale(), pageLink, 
                null, null, null, null, 0, false);
        
        if(letter != null && StringUtils.isEmpty(letter)) {
            letter = null;
        }
        
        return new WeblogsPager(
            urlStrategy,
            pagerUrl,
            letter,
            weblogRequest.getLocale(),
            sinceDays,
            pageNum, 
            length);
    }   
