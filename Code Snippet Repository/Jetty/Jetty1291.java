    protected String addCSV(QuotedCSV existing,String... values)
    {
        // remove any existing values from the new values
        boolean add = true;
        if (existing!=null && !existing.isEmpty())
        {
            add = false;
        
            for (int i=values.length;i-->0;)
            {
                String unquoted = QuotedCSV.unquote(values[i]);
                if (existing.getValues().contains(unquoted))
                    values[i] = null;
                else
                    add = true;
            }
        }
            
        if (add)
        {
            StringBuilder value = new StringBuilder();
            for (String v:values)
            {
                if (v==null)
                    continue;
                if (value.length()>0)
                    value.append(", ");
                value.append(v);
            }
            if (value.length()>0)
                return value.toString();
        }
        
        return null;
    }
