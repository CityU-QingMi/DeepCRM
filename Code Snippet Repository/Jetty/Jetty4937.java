    public boolean removeValue(String name,V value)
    {
        List<V> lo = get(name);
        if((lo == null)||(lo.isEmpty())) {
            return false;
        }
        boolean ret = lo.remove(value);
        if(lo.isEmpty()) {
            remove(name);
        } else {
            put(name,lo);
        }
        return ret;
    }
