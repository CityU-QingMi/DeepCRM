    public List<KeyValueObject> getStatusOptions() {
        List<KeyValueObject> opts = new ArrayList<KeyValueObject>();
        
        opts.add(new KeyValueObject("ALL", getText("weblogEntryQuery.label.allEntries")));
        opts.add(new KeyValueObject("DRAFT", getText("weblogEntryQuery.label.draftOnly")));
        opts.add(new KeyValueObject("PUBLISHED", getText("weblogEntryQuery.label.publishedOnly")));
        opts.add(new KeyValueObject("PENDING", getText("weblogEntryQuery.label.pendingOnly")));
        opts.add(new KeyValueObject("SCHEDULED", getText("weblogEntryQuery.label.scheduledOnly")));
        
        return opts;
    }
