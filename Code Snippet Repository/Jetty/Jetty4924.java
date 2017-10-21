    public static String[] toStringArray(Object list)
    {
        if (list==null)
            return __EMTPY_STRING_ARRAY;
        
        if (list instanceof List)
        {
            List<?> l = (List<?>)list;
            String[] a = new String[l.size()];
            for (int i=l.size();i-->0;)
            {
                Object o=l.get(i);
                if (o!=null)
                    a[i]=o.toString();
            }
            return a;
        }
        
        return new String[] {list.toString()};
    }
