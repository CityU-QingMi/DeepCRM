    @SuppressWarnings("")
    public static<E> Iterator<E> iterator(Object list)
    {
        if (list==null)
        {
            List<E> empty=Collections.emptyList();
            return empty.iterator();
        }
        if (list instanceof List)
        {
            return ((List<E>)list).iterator();
        }
        List<E> l=getList(list);
        return l.iterator();
    }
