    public Date getLastUpdated() {
        if (lastUpdated == null) {
            // feeds are sorted by pubtime, so first might not be last updated
            List<MediaFile> items = getItems();
            if (items != null && items.size() > 0) {
                Timestamp newest = items.get(0).getLastUpdated();
                for (MediaFile file : items) {
                    if (file.getLastUpdated().after(newest)) {
                        newest = file.getLastUpdated();
                    }
                }
                lastUpdated = new Date(newest.getTime());
            } else {
                // no update so we assume it's brand new
                lastUpdated = new Date();
            }
        }
        return lastUpdated;
    }
