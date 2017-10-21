    @SuppressWarnings("")
    public static <E> E get(Object list, int i)
    {
        if (list==null)
            throw new IndexOutOfBoundsException();
       
        if (list instanceof List)
            return (E)((List<?>)list).get(i);

        if (i==0)
            return (E)list;
        
        throw new IndexOutOfBoundsException();
    }
