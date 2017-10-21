    public String convertIdentifier (String identifier)
    {
        if (identifier == null)
            return null;
        
        if (_dbName == null)
            throw new IllegalStateException ("DbAdaptor missing metadata");
        
        if (_isLower)
            return identifier.toLowerCase(Locale.ENGLISH);
        if (_isUpper)
            return identifier.toUpperCase(Locale.ENGLISH);

        return identifier;
    }
