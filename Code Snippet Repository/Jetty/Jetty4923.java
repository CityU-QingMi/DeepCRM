    @SuppressWarnings("")
    public static<E> List<E> getList(Object list, boolean nullForEmpty)
    {
        if (list==null)
        {
            if (nullForEmpty)
                return null;
            return Collections.emptyList();
        }
        if (list instanceof List)
            return (List<E>)list;
        
        return (List<E>)Collections.singletonList(list);
    }
