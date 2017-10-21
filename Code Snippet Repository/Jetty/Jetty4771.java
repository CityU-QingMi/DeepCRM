    public Set<Map.Entry<String,V>> entrySet()
    {
        Set<Map.Entry<String,V>> entries = new HashSet<>();
        for (int r=0;r<=_rows;r++)
        {
            if (_key[r]!=null && _value[r]!=null)
                entries.add(new AbstractMap.SimpleEntry<>(_key[r],_value[r]));
        }
        return entries;
    }
