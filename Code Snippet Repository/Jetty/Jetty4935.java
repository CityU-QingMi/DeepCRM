    public String getString(String name)
    {
        List<V> vals =get(name);
        if ((vals == null) || (vals.isEmpty()))
        {
            return null;
        }
        
        if (vals.size() == 1)
        {
            // simple form.
            return vals.get(0).toString();
        }
        
        // delimited form
        StringBuilder values=new StringBuilder(128);
        for (V e : vals)
        {
            if (e != null)
            {
                if (values.length() > 0)
                    values.append(',');
                values.append(e.toString());
            }
        }   
        return values.toString();
    }
