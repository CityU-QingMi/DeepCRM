    public WeblogEntriesListPager(
            URLStrategy    strat,
            String         baseUrl,
            Weblog         queryWeblog,
            User           queryUser,
            String         queryCat,
            List<String>   queryTags,
            String         locale,
            int            sinceDays,
            int            pageNum,
            int            length) {
        
        super(strat, baseUrl, pageNum);
        
        // store the data
        this.queryWeblog = queryWeblog;
        this.queryUser = queryUser;
        this.queryCat = queryCat;
        this.queryTags = queryTags;
        this.locale = locale;
        this.sinceDays = sinceDays;
        this.length = length;
        
        // initialize the pager collection
        getItems();
    }
