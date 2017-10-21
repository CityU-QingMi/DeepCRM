    @Override
    public void copyTo(File destination)
            throws IOException
    {
        if (isDirectory())
        {
            IO.copyDir(getFile(),destination);
        }
        else
        {
            if (destination.exists())
                throw new IllegalArgumentException(destination+" exists");
            IO.copy(getFile(),destination);
        }
    }
