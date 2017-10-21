    public Set<String> getFieldNamesCollection()
    {
        final Set<String> set = new HashSet<>(_size);
        for (HttpField f : this)
        {
            if (f!=null)
                set.add(f.getName());
        }
        return set;
    }
