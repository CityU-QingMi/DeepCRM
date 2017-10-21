    private void writeEncoded(char[] ca,int offset, int length)
        throws IOException
    {
        if (_bout==null)
        {
            _bout = new ByteArrayOutputStream2(2*length);
            _writer = new OutputStreamWriter(_bout,StandardCharsets.ISO_8859_1);
        }
        else
            _bout.reset();
        _writer.write(ca,offset,length);
        _writer.flush();
        ensureSpareCapacity(_bout.getCount());
        System.arraycopy(_bout.getBuf(),0,_buf,_size,_bout.getCount());
        _size+=_bout.getCount();
    }
