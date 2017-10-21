    public static <T> T[] removeNulls(T[] array)
    {
        for (T t : array)
        {
            if (t==null)
            {
                List<T> list = new ArrayList<>();
                for (T t2:array)
                    if (t2!=null)
                        list.add(t2);
                return list.toArray(Arrays.copyOf(array,list.size()));
            }
        }
        return array;
    }
