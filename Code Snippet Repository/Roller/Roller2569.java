    private Hashtable createPostStruct(WeblogEntry entry, String userid) {
        
        String permalink =
            WebloggerRuntimeConfig.getAbsoluteContextURL() + entry.getPermaLink();
        
        Hashtable struct = new Hashtable();
        struct.put("title", entry.getTitle());
        if (entry.getLink() != null) {
            struct.put("link", Utilities.escapeHTML(entry.getLink()));
        }
        struct.put("description", entry.getText());
        if (entry.getPubTime() != null) {
            struct.put("pubDate", entry.getPubTime());
            struct.put("dateCreated", entry.getPubTime());
        }
        struct.put("guid", Utilities.escapeHTML(permalink));
        struct.put("permaLink", Utilities.escapeHTML(permalink));
        struct.put("postid", entry.getId());
        
        struct.put("userid", entry.getCreator().getUserName());
        struct.put("author", entry.getCreator().getEmailAddress());
       
        if ( entry.getCategory() != null ) {
            Vector catArray = new Vector();
            catArray.addElement(entry.getCategory().getName());
            struct.put("categories", catArray);

        } else {
            mLogger.warn("Entry " + entry.getId() + " has null category");
        }

        return struct;
    }
