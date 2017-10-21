    public SearchResultsFeedPager(URLStrategy strat, String baseUrl, int pageNum,
            WeblogFeedRequest feedRequest, List entries, boolean more) {
        
        super(strat, baseUrl, pageNum);
        
        this.url = baseUrl;
        
        this.feedRequest = feedRequest;
        
        // store search results
        this.entries = entries;
        
        // data from search request
        this.weblog = feedRequest.getWeblog();
        
        // does this pager have more results?
        this.moreResults = more;
        
        // get a message utils instance to handle i18n of messages
        Locale viewLocale = null;
        if(feedRequest.getLocale() != null) {
            String[] langCountry = feedRequest.getLocale().split("_");
            if(langCountry.length == 1) {
                viewLocale = new Locale(langCountry[0]);
            } else if(langCountry.length == 2) {
                viewLocale = new Locale(langCountry[0], langCountry[1]);
            }
        } else {
            viewLocale = weblog.getLocaleInstance();
        }
        this.messageUtils = I18nMessages.getMessages(viewLocale);
    }
