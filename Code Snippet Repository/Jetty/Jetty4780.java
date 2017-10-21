    public static<T> T[] removeFromArray(T[] array, Object item)
    {
        if (item==null || array==null)
            return array;
        for (int i=array.length;i-->0;)
        {
            if (item.equals(array[i]))
            {
                Class<?> c = array==null?item.getClass():array.getClass().getComponentType();
                @SuppressWarnings("unchecked")
                T[] na = (T[])Array.newInstance(c, Array.getLength(array)-1);
                if (i>0)
                    System.arraycopy(array, 0, na, 0, i);
                if (i+1<array.length)
                    System.arraycopy(array, i+1, na, i, array.length-(i+1));
                return na;
            }
        }
        return array;
    }
