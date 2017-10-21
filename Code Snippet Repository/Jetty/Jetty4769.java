    @Override
    public Set<String> keySet()
    {
        Set<String> keys = new HashSet<>();

        for (int r=0;r<=_rows;r++)
        {
            if (_key[r]!=null && _value[r]!=null)
                keys.add(_key[r]);
        }
        return keys;
    }
