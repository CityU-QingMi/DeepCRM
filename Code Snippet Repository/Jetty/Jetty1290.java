    public boolean addCSV(String name,String... values)
    {
        QuotedCSV existing = null;
        for (HttpField f : this)
        {
            if (f.getName().equalsIgnoreCase(name))
            {
                if (existing==null)
                    existing = new QuotedCSV(false);
                existing.addValue(f.getValue());
            }
        }
        String value = addCSV(existing,values);
        if (value!=null)
        {
            add(name,value);
            return true;
        }
        return false;
    }
