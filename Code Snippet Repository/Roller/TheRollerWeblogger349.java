    public void addTag(String name) throws WebloggerException {
        Locale localeObject = getWeblog() != null ? getWeblog()
                .getLocaleInstance() : Locale.getDefault();
        name = Utilities.normalizeTag(name, localeObject);
        if (name.length() == 0) {
            return;
        }

        for (MediaFileTag tag : getTags()) {
            if (tag.getName().equals(name)) {
                return;
            }
        }

        MediaFileTag tag = new MediaFileTag();
        tag.setName(name);
        tag.setMediaFile(this);

        tagSet.add(tag);

        addedTags.add(name);
    }
