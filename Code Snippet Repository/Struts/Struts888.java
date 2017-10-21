    public void flushBuffer()
            throws IOException
    {
        //assert out!=null
        if( out==null ) {
            throw new IOException( "Buffer overflow, no sink " + limit + " " +
                    buff.length  );
        }
        out.realWriteBytes( buff, start, end-start );
        end=start;
    }
