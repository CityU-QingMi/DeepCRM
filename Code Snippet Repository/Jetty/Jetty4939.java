    @Override
    public String toString()
    {
        Iterator<Entry<String, List<V>>> iter = entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        boolean delim = false;
        while (iter.hasNext())
        {
            Entry<String, List<V>> e = iter.next();
            if (delim)
            {
                sb.append(", ");
            }
            String key = e.getKey();
            List<V> vals = e.getValue();
            sb.append(key);
            sb.append('=');
            if (vals.size() == 1)
            {
                sb.append(vals.get(0));
            }
            else
            {
                sb.append(vals);
            }
            delim = true;
        }
        sb.append('}');
        return sb.toString();
    }
