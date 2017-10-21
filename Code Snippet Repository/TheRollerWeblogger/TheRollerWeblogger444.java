    public List<WeblogEntryWrapper> getRecentWeblogEntriesByTag(String tag,int length) {
        List<WeblogEntry> unwrapped = pojo.getRecentWeblogEntriesByTag(tag,length);
        List<WeblogEntryWrapper> wrapped = new ArrayList<WeblogEntryWrapper>(unwrapped.size());

        int i = 0;
        for (WeblogEntry we : unwrapped) {
            wrapped.add(i,WeblogEntryWrapper.wrap(we, urlStrategy));
            i++;
        }
        return wrapped;
    }
