    public void ensureSpareCapacity(int n)
        throws IOException
    {
        if (_size+n>_buf.length)
        {
            if (_fixed)
                throw new IOException("Buffer overflow: "+_buf.length);
            _buf=Arrays.copyOf(_buf,(_buf.length+n)*4/3);
        }
    }
