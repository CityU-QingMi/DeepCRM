    public void cleanUp() {
        Set<String> names = files.keySet();
        for (String name : names) {
            List<FileItem> items = files.get(name);
            for (FileItem item : items) {
                LOG.debug("Removing file {} {}", name, item );
                if (!item.isInMemory()) {
                    item.delete();
                }
            }
        }
    }
