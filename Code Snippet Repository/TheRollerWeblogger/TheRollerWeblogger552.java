    public PlanetEntriesPager(
            URLStrategy    strat,
            String         feedURL,
            String         groupHandle,
            String         baseUrl,
            int            sinceDays,
            int            page,
            int            length) {
        
        super(strat, baseUrl, page);
        
        this.feedURL = feedURL;
        this.groupHandle = groupHandle;
        this.sinceDays = sinceDays;
        this.length = length;
        
        // initialize the collection
        getItems();
    }
