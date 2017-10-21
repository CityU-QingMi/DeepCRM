    public static<T> T[] prependToArray(T item, T[] array, Class<?> type)
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
            Class<?> c = array.getClass().getComponentType();
            @SuppressWarnings("unchecked")
            T[] na = (T[])Array.newInstance(c, Array.getLength(array)+1);
            System.arraycopy(array, 0, na, 1, array.length);
            na[0]=item;
            return na;
        }
    }
