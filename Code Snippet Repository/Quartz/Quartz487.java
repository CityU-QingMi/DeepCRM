    public int compareTo(Trigger other) {

        if(other.getKey() == null && getKey() == null)
            return 0;
        if(other.getKey() == null)
            return -1;
        if(getKey() == null)
            return 1;
        
        return getKey().compareTo(other.getKey());
    }
