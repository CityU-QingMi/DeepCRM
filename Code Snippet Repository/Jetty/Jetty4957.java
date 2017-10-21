    @Override
    public void close()
         throws IOException
    {
        try
        {
            if (inPart)
                out.write(__CRLF);
            out.write(__DASHDASH);
            out.write(boundary);
            out.write(__DASHDASH);
            out.write(__CRLF);
            inPart=false;
        }
        finally
        {
            super.close();
        }
    }
