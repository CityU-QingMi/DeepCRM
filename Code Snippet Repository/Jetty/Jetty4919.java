    @SuppressWarnings("")
    public static Object add(Object list, int index, Object item)
    {
        if (list==null)
        {
            if (index>0 || item instanceof List || item==null)
            {
                List<Object> l = new ArrayList<Object>();
                l.add(index,item);
                return l;
            }
            return item;
        }

        if (list instanceof List)
        {
            ((List<Object>)list).add(index,item);
            return list;
        }

        List<Object> l=new ArrayList<Object>();
        l.add(list);
        l.add(index,item);
        return l;
    }
