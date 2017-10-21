    public static Object remove(Object list, int i)
    {
        if (list==null)
            return null;

        if (list instanceof List)
        {
            List<?> l = (List<?>)list;
            l.remove(i);
            if (l.size()==0)
                return null;
            return list;
        }

        if (i==0)
            return null;
        return list;
    }
