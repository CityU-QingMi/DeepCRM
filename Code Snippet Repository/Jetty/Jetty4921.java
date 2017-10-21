    public static Object remove(Object list, Object o)
    {
        if (list==null)
            return null;

        if (list instanceof List)
        {
            List<?> l = (List<?>)list;
            l.remove(o);
            if (l.size()==0)
                return null;
            return list;
        }

        if (list.equals(o))
            return null;
        return list;
    }
