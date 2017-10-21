    public UsersPager(
            URLStrategy    strat,
            String         baseUrl,
            String         locale,
            int            sinceDays,
            int            page,
            int            length) {
        
        super(strat, baseUrl, page);
        
        this.length = length;
        
        // initialize the collection
        getItems();
    }
