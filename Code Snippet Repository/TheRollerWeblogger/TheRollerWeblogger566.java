    public Date getLastUpdated() {
        if (lastUpdated == null) {
            // feeds are sorted by pubtime, so first might not be last updated
            List<WeblogEntryWrapper> items = getItems();
            if (getItems() != null && getItems().size() > 0) {
                Timestamp newest = (getItems().get(0)).getUpdateTime();
                for (WeblogEntryWrapper e : items) {
                    if (e.getUpdateTime().after(newest)) {
                        newest = e.getPubTime();
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
