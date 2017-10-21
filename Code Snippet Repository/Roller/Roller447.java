    public void removeMediaFileTag(String name, MediaFile entry)
            throws WebloggerException {

        for (Iterator it = entry.getTags().iterator(); it.hasNext();) {
            MediaFileTag tag = (MediaFileTag) it.next();
            if (tag.getName().equals(name)) {

                // Call back the entity to adjust its internal state
                entry.onRemoveTag(name);

                // Refresh it from database
                this.strategy.remove(tag);

                // Refresh it from the collection
                it.remove();
            }
        }
    }
