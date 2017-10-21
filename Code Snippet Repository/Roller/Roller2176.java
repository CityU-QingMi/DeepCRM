    public UsersPager(
            URLStrategy    strat,
            String         baseUrl,
            String         letter,
            String         locale,
            int            sinceDays,
            int            page,
            int            length) {
        
        super(strat, baseUrl, page);
        
        this.letter = letter;
        this.length = length;
        
        // initialize the collection
        getItems();
    }
