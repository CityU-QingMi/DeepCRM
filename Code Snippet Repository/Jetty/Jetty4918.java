    @SuppressWarnings("")
    public static<E> ListIterator<E> listIterator(Object list)
    {
        if (list==null)
        {
            List<E> empty=Collections.emptyList();
            return empty.listIterator();
        }
        if (list instanceof List)
            return ((List<E>)list).listIterator();

        List<E> l=getList(list);
        return l.listIterator();
    }
