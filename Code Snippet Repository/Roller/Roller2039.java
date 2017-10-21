    public List retrieveWeblogEntries(boolean publishedOnly)
            throws WebloggerException {
        
        List initialCollection = this.pojo.retrieveWeblogEntries(publishedOnly);
        
        // iterate through and wrap
        // we force the use of an ArrayList because it should be good enough to cover
        // for any Collection type we encounter.
        ArrayList wrappedCollection = new ArrayList(initialCollection.size());
        Iterator it = initialCollection.iterator();
        int i = 0;
        while(it.hasNext()) {
            wrappedCollection.add(i,WeblogEntryWrapper.wrap((WeblogEntry) it.next(), urlStrategy));
            i++;
        }
        
        return wrappedCollection;
    }
