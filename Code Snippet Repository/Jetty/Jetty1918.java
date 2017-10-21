    public void setProperties (Properties properties)
    {
        Iterator entries = properties.entrySet().iterator();
        while (entries.hasNext())
        {
            Map.Entry e = (Map.Entry)entries.next();
            StringRefAddr sref = (StringRefAddr)get((String)e.getKey());
            if (sref != null)
                throw new RuntimeException ("property "+e.getKey()+" already set on Session reference, can't be changed");
            add(new StringRefAddr((String)e.getKey(), (String)e.getValue()));
        }
    }
