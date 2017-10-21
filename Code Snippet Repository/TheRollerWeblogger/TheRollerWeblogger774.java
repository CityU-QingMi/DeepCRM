    public List<KeyValueObject> getCommentDaysList() {
        
        List<KeyValueObject> opts = new ArrayList<KeyValueObject>();
        
        opts.add(new KeyValueObject(0, getText("weblogEdit.unlimitedCommentDays")));
        opts.add(new KeyValueObject(3, getText("weblogEdit.days3")));
        opts.add(new KeyValueObject(7, getText("weblogEdit.days7")));
        opts.add(new KeyValueObject(14, getText("weblogEdit.days14")));
        opts.add(new KeyValueObject(30, getText("weblogEdit.days30")));
        opts.add(new KeyValueObject(60, getText("weblogEdit.days60")));
        opts.add(new KeyValueObject(90, getText("weblogEdit.days90")));
        
        return opts;
    }
