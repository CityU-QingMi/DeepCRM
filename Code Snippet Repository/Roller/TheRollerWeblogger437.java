    public List getTags() {
        // Sort by name
        Set<WeblogEntryTag> initialCollection = new TreeSet<WeblogEntryTag>(new WeblogEntryTagComparator());
        initialCollection.addAll(this.pojo.getTags());
        
        // iterate through and wrap
        // we force the use of an ArrayList because it should be good enough to cover
        // for any Collection type we encounter.
        ArrayList wrappedCollection = new ArrayList(initialCollection.size());
        Iterator it = initialCollection.iterator();
        int i = 0;
        while(it.hasNext()) {
            wrappedCollection.add(i,WeblogEntryTagWrapper.wrap((WeblogEntryTag) it.next()));
            i++;
        }
        
        return wrappedCollection;
    }
