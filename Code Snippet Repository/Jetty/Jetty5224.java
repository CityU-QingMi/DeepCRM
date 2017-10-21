    public void copyTo(File destination)
        throws IOException
    {
        if (destination.exists())
            throw new IllegalArgumentException(destination + " exists");
        
        try (OutputStream out = new FileOutputStream(destination))
        {
            writeTo(out,0,-1);
        }
    }
