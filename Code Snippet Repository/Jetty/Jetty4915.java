    @SuppressWarnings("")
    public static Object add(Object list, Object item)
    {
        if (list==null)
        {
            if (item instanceof List || item==null)
            {
                List<Object> l = new ArrayList<Object>();
                l.add(item);
                return l;
            }

            return item;
        }

        if (list instanceof List)
        {
            ((List<Object>)list).add(item);
            return list;
        }

        List<Object> l=new ArrayList<Object>();
        l.add(list);
        l.add(item);
        return l;    
    }
