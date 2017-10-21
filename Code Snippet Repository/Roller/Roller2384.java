    public String view() {
        try {
            MediaFileManager manager = WebloggerFactory.getWeblogger()
                    .getMediaFileManager();
            if (!StringUtils.isEmpty(viewDirectoryId)) {
                setDirectoryId(viewDirectoryId);
                setCurrentDirectory(manager
                        .getMediaFileDirectory(viewDirectoryId));
            }
        } catch (WebloggerException ex) {
            log.error("Error looking up directory", ex);
        }
        return execute();
    }
