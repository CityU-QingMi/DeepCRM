    public SharedThemeTemplate(String id, ComponentType action, String name,
            String desc, String contents, String link, Date date, 
            boolean hidden, boolean navbar) {
        
        this.id = id;
        this.action = action;
        this.name = name;
        this.description = desc;
        this.contents = contents;
        this.link = link;
        this.lastModified = date;
        this.hidden = hidden;
        this.navbar = navbar;
    }
