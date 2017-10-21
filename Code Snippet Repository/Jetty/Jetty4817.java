    public static boolean isMappedBuffer(ByteBuffer buffer)
    {
        if (!(buffer instanceof MappedByteBuffer))
            return false;
        MappedByteBuffer mapped = (MappedByteBuffer) buffer;

        try 
        {
            // Check if it really is a mapped buffer
            mapped.isLoaded();
            return true;
        }
        catch(UnsupportedOperationException e)
        {
            return false;
        }
    }
