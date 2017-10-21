    public List getEntryAttributes() {
        Set initialCollection = this.pojo.getEntryAttributes();
        
        // iterate through and wrap
        // we force the use of an ArrayList because it should be good enough to cover
        // for any Collection type we encounter.
        ArrayList wrappedCollection = new ArrayList(initialCollection.size());
        Iterator it = initialCollection.iterator();
        int i = 0;
        while(it.hasNext()) {
            wrappedCollection.add(i,WeblogEntryAttributeWrapper.wrap((WeblogEntryAttribute) it.next()));
            i++;
        }
        
        return wrappedCollection;
    }
