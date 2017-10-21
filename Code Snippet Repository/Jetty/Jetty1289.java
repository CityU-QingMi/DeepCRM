    public boolean addCSV(HttpHeader header,String... values)
    {
        QuotedCSV existing = null;
        for (HttpField f : this)
        {
            if (f.getHeader()==header)
            {
                if (existing==null)
                    existing = new QuotedCSV(false);
                existing.addValue(f.getValue());
            }
        }
        
        String value = addCSV(existing,values);
        if (value!=null)
        {
            add(header,value);
            return true;
        }
        return false;
    }
