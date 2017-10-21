    @Override 
    public ReadableByteChannel getReadableByteChannel() throws IOException
    {
        if(_resources==null)
            throw new IllegalStateException("*resources* not set.");
        
        for(Resource r : _resources)
        {
            ReadableByteChannel channel = r.getReadableByteChannel();
            if(channel!=null)
                return channel;
        }
        return null;
    }
