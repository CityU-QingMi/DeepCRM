    public CommentsPager(
            URLStrategy    strat,
            String         baseUrl,
            Weblog         weblog,
            int            sinceDays,
            int            page,
            int            length) {
        
        super(strat, baseUrl, page);
        
        this.weblog = weblog;
        this.sinceDays = sinceDays;
        this.length = length;
        
        // initialize the collection
        getItems();
    }
