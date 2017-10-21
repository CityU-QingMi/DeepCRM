    private File asFile(Object fileattr)
    {
        if (fileattr == null)
        {
            return null;
        }
        if (fileattr instanceof File)
        {
            return (File)fileattr;
        }
        if (fileattr instanceof String)
        {
            return new File((String)fileattr);
        }
        return null;
    }
