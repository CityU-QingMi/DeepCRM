    @Override
    public void write (char[] s,int offset, int length) throws IOException
    {
        HttpOutput out = _out;
        if (length==0 && out.isAllContentWritten())
        {
            close();
            return;
        }

        if (length==1)
        {
            int c=s[offset];
            out.write(c<256?c:'?');
            return;
        }
        
        while (length > 0)
        {
            _bytes.reset();
            int chars = length>MAX_OUTPUT_CHARS?MAX_OUTPUT_CHARS:length;

            byte[] buffer=_bytes.getBuf();
            int bytes=_bytes.getCount();

            if (chars>buffer.length-bytes)
                chars=buffer.length-bytes;

            for (int i = 0; i < chars; i++)
            {
                int c = s[offset+i];
                buffer[bytes++]=(byte)(c<256?c:'?');
            }
            if (bytes>=0)
                _bytes.setCount(bytes);

            _bytes.writeTo(out);
            length-=chars;
            offset+=chars;
        }
    }
