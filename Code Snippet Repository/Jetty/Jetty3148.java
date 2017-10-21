    public String getLongType ()
    {
        if (_longType != null)
            return _longType;

        if (_dbName == null)
            throw new IllegalStateException ("DbAdaptor missing metadata");
        
        if (_dbName.startsWith("oracle"))
            return "number(20)";

        return "bigint";
    }
