    @Override
    public boolean remove(Object o)
    {
        if (!(o instanceof String))
            return false;
        String pattern = (String)o;

        Entry entry = _entries.remove(pattern);
        if (entry==null)
            return false;

        List<Entry> saved = new ArrayList<>(_entries.values());
        clear();
        for (Entry e:saved)
            add(e);
        return true;
    }
