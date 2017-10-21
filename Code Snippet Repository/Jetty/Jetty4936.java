    public boolean addAllValues(MultiMap<V> map)
    {
        boolean merged = false;

        if ((map == null) || (map.isEmpty()))
        {
            // done
            return merged;
        }

        for (Map.Entry<String, List<V>> entry : map.entrySet())
        {
            String name = entry.getKey();
            List<V> values = entry.getValue();

            if (this.containsKey(name))
            {
                merged = true;
            }

            this.addValues(name,values);
        }

        return merged;
    }
