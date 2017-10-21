    public Map<String,String[]> toStringArrayMap()
    {
        HashMap<String,String[]> map = new HashMap<String,String[]>(size()*3/2)
        {
            @Override
            public String toString()
            {
                StringBuilder b=new StringBuilder();
                b.append('{');
                for (String k:super.keySet())
                {
                    if(b.length()>1)
                        b.append(',');
                    b.append(k);
                    b.append('=');
                    b.append(Arrays.asList(super.get(k)));
                }

                b.append('}');
                return b.toString();
            }
        };
        
        for(Map.Entry<String,List<V>> entry: entrySet())
        {
            String[] a = null;
            if (entry.getValue() != null)
            {
                a = new String[entry.getValue().size()];
                a = entry.getValue().toArray(a);
            }
            map.put(entry.getKey(),a);
        }
        return map;
    }
