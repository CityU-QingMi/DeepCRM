    public static ByteBuffer toBuffer(Resource resource,boolean direct) throws IOException
    {
        int len=(int)resource.length();
        if (len<0)
            throw new IllegalArgumentException("invalid resource: "+String.valueOf(resource)+" len="+len);
        
        ByteBuffer buffer = direct?BufferUtil.allocateDirect(len):BufferUtil.allocate(len);

        int pos=BufferUtil.flipToFill(buffer);
        if (resource.getFile()!=null)
            BufferUtil.readFrom(resource.getFile(),buffer);
        else
        {
            try (InputStream is = resource.getInputStream();)
            {
                BufferUtil.readFrom(is,len,buffer);
            }
        }
        BufferUtil.flipToFlush(buffer,pos);
        
        return buffer;
    }
