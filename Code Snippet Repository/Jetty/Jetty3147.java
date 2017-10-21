    public String getBlobType ()
    {
        if (_blobType != null)
            return _blobType;

        if (_dbName.startsWith("postgres"))
            return "bytea";

        return "blob";
    }
