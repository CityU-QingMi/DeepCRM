    public List<WeblogEntryWrapper> getRecentWeblogEntries(String cat,int length) {
        List<WeblogEntry> unwrapped = this.pojo.getRecentWeblogEntries(cat, length);
        List<WeblogEntryWrapper> wrapped = new ArrayList<WeblogEntryWrapper>(unwrapped.size());

        int i = 0;
        for (WeblogEntry we : unwrapped) {
            wrapped.add(i,WeblogEntryWrapper.wrap(we, urlStrategy));
            i++;
        }
        return wrapped;
    }
