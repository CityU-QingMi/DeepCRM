    public void sort(Collection<T> list)
    {
        List<T> sorted = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        Comparator<T> comparator = new InitialOrderComparator<>(list);
        
        // Visit all items in the list
        for (T t : list)
            visit(t,visited,sorted,comparator);
        
        list.clear();
        list.addAll(sorted);
    }
