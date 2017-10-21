    protected void throwIfError ()
    throws IOException
    {
        if (_err != null)
        {
            if (_err instanceof IOException)
                throw (IOException)_err;
            if (_err instanceof IllegalStateException)
                throw (IllegalStateException)_err;
            throw new IllegalStateException(_err);
        }
    }
