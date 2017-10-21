    public V getValue(String name,int i)
    {
        List<V> vals = getValues(name);
        if(vals == null) {
            return null;
        }
        if (i==0 && vals.isEmpty()) {
            return null;
        }
        return vals.get(i);
    }
