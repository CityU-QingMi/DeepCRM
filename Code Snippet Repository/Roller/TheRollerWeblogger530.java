    public Pager getCommentsPager(int sinceDays, int length) {
        
        String pagerUrl;
        if (feedRequest != null) {
            pagerUrl = urlStrategy.getWeblogFeedURL(weblog, 
                    weblogRequest.getLocale(), feedRequest.getType(),
                    feedRequest.getFormat(), null, null, null,
                    feedRequest.isExcerpts(), true);
        } else {        
            pagerUrl = urlStrategy.getWeblogPageURL(weblog, 
                weblogRequest.getLocale(), pageLink, 
                null, null, null, null, 0, false);
        }
        
        return new CommentsPager(
            urlStrategy,
            pagerUrl,
            null,
            sinceDays,
            pageNum, 
            length);
    }     
