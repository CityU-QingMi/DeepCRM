    public static Object ensureSize(Object list, int initialSize)
    {
        if (list==null)
            return new ArrayList<Object>(initialSize);
        if (list instanceof ArrayList)
        {
            ArrayList<?> ol=(ArrayList<?>)list;
            if (ol.size()>initialSize)
                return ol;
            ArrayList<Object> nl = new ArrayList<Object>(initialSize);
            nl.addAll(ol);
            return nl;
        }
        List<Object> l= new ArrayList<Object>(initialSize);
        l.add(list);
        return l;    
    }
