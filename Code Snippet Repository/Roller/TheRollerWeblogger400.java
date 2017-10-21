    public WeblogCategory(
            Weblog weblog,
            String name,
            String description,
            String image) {
        
        this.name = name;
        this.description = description;
        this.image = image;
        
        this.weblog = weblog;
        weblog.getWeblogCategories().add(this);
        calculatePosition();
    }
