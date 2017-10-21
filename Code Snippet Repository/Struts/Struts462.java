    private FileManager lookupFileManager() {
        Set<String> names = container.getInstanceNames(FileManager.class);
        LOG.debug("Found following implementations of FileManager interface: {}", names);
        Set<FileManager> internals = new HashSet<>();
        Set<FileManager> users = new HashSet<>();
        for (String fmName : names) {
            FileManager fm = container.getInstance(FileManager.class, fmName);
            if (fm.internal()) {
                internals.add(fm);
            } else {
                users.add(fm);
            }
        }
        for (FileManager fm : users) {
            if (fm.support()) {
                LOG.debug("Using FileManager implementation [{}]", fm.getClass().getSimpleName());
                return fm;
            }
        }
        LOG.debug("No user defined FileManager, looking up for internal implementations!");
        for (FileManager fm : internals) {
            if (fm.support()) {
                return fm;
            }
        }
        return null;
    }
