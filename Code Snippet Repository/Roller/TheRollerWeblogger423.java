    public WeblogEntryTag(
            String id,
            Weblog website,
            WeblogEntry weblogEntry,
            User user, 
            String name,
            Timestamp time) {
        //this.id = id;
        this.website = website;
        this.weblogEntry = weblogEntry;
        this.userName = user.getUserName();
        this.name = name;
        this.time = time;
    }
