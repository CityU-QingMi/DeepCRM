    public void updateTags(List<String> updatedTags) throws WebloggerException {

        if (updatedTags == null) {
            return;
        }

        HashSet<String> newTags = new HashSet<String>(updatedTags.size());
        Locale localeObject = getWeblog() != null ? getWeblog()
                .getLocaleInstance() : Locale.getDefault();

        for (String inName : updatedTags) {
            newTags.add(Utilities.normalizeTag(inName, localeObject));
        }

        HashSet<String> removeTags = new HashSet<String>();

        // remove old ones no longer passed.
        for (MediaFileTag tag : getTags()) {
            if (!newTags.contains(tag.getName())) {
                removeTags.add(tag.getName());
            } else {
                newTags.remove(tag.getName());
            }
        }

        MediaFileManager mediaManager = WebloggerFactory.getWeblogger()
                .getMediaFileManager();

        for (String tag : removeTags) {
            mediaManager.removeMediaFileTag(tag, this);
        }

        for (String tag : newTags) {
            addTag(tag);
        }
    }
