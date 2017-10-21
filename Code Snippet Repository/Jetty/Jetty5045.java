    public void sort(T[] array)
    {
        List<T> sorted = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        Comparator<T> comparator = new InitialOrderComparator<>(array);
        
        // Visit all items in the array
        for (T t : array)
            visit(t,visited,sorted,comparator);
        
        sorted.toArray(array);
    }
