    public void saveFolder(WeblogBookmarkFolder folder) throws WebloggerException {

        // If new folder make sure name is unique
        if ((folder.getId() == null || this.getFolder(folder.getId()) == null) && isDuplicateFolderName(folder)) {
            throw new WebloggerException("Duplicate folder name");
        }

        this.strategy.store(folder);

        // update weblog last modified date.  date updated by saveWeblog()
        roller.getWeblogManager().saveWeblog(folder.getWeblog());
    }
