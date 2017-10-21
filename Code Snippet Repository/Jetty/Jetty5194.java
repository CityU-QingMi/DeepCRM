    @Override
    public long lastModified()
    {
        if (checkConnection() && _file!=null)
        {
            if (exists() && _entry!=null)
                return _entry.getTime();
            return _file.lastModified();
        }
        return -1;
    }
