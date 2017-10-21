    public MediaFileDirectory(Weblog weblog, String name,
            String desc) {

        this.id = UUIDGenerator.generateUUID();
        this.name = name;
        this.description = desc;

        this.weblog = weblog;
        weblog.getMediaFileDirectories().add(this);
    }
