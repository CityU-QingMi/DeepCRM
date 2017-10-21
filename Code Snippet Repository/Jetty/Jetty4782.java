    public static<T> T[] addToArray(T[] array, T item, Class<?> type)
    {
        if (array==null)
        {
            if (type==null && item!=null)
                type= item.getClass();
            @SuppressWarnings("unchecked")
            T[] na = (T[])Array.newInstance(type, 1);
            na[0]=item;
            return na;
        }
        else
        {
            T[] na = Arrays.copyOf(array,array.length+1);
            na[array.length]=item;
            return na;
        }
    }
