    public boolean containsSimpleValue(V value)
    {
        for (List<V> vals : values())
        {
            if ((vals.size() == 1) && vals.contains(value))
            {
                return true;
            }
        }
        return false;
    }
