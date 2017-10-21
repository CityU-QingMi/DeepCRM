    protected void refreshAllDirectories() {
        try {
            MediaFileManager mgr = WebloggerFactory.getWeblogger()
                    .getMediaFileManager();
            List<MediaFileDirectory> directories = mgr.getMediaFileDirectories(getActionWeblog());
            List<MediaFileDirectory> sortedDirList = new ArrayList<MediaFileDirectory>();
            sortedDirList.addAll(directories);
            Collections.sort(sortedDirList, new MediaFileDirectoryComparator(
                    DirectoryComparatorType.NAME));
            setAllDirectories(sortedDirList);
        } catch (WebloggerException ex) {
            log.error("Error looking up media file directories", ex);
        }
    }
