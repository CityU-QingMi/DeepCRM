    public MediaFilesPager(
            URLStrategy    strat,
            String         baseUrl,
            int            page,
            int            length) {
        
        super(strat, baseUrl, page);
        this.length = length;
        
        // initialize the collection
        getItems();
    }
