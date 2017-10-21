    public static boolean contains(Object list,Object item)
    {
        if (list==null)
            return false;
        
        if (list instanceof List)
            return ((List<?>)list).contains(item);

        return list.equals(item);
    }
