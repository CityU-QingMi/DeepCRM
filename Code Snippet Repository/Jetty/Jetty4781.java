    public static<T> T[] add(T[] array1, T[] array2)
    {
        if (array1==null || array1.length==0)
            return array2;
        if (array2==null || array2.length==0)
            return array1;
                    
        T[] na = Arrays.copyOf(array1,array1.length+array2.length);
        System.arraycopy(array2,0,na,array1.length,array2.length);
        return na;
    }
