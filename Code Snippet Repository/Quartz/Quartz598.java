    public int compareTo(Key<T> o) {
        
        if(group.equals(DEFAULT_GROUP) && !o.group.equals(DEFAULT_GROUP))
            return -1;
        if(!group.equals(DEFAULT_GROUP) && o.group.equals(DEFAULT_GROUP))
            return 1;
            
        int r = group.compareTo(o.getGroup());
        if(r != 0)
            return r;
        
        return name.compareTo(o.getName());
    }
