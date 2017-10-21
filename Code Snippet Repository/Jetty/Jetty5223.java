    public void writeTo(OutputStream out,long start,long count)
        throws IOException
    {
        try (InputStream in = getInputStream())
        {
            in.skip(start);
            if (count<0)
                IO.copy(in,out);
            else
                IO.copy(in,out,count);
        }
    }    
