    @Override
    public void write (char[] s,int offset, int length) throws IOException
    {
        HttpOutput out = _out;
        if (length==0 && out.isAllContentWritten())
        {
            out.close();
            return;
        }
            
        while (length > 0)
        {
            _bytes.reset();
            int chars = length>MAX_OUTPUT_CHARS?MAX_OUTPUT_CHARS:length;

            _converter.write(s, offset, chars);
            _converter.flush();
            _bytes.writeTo(out);
            length-=chars;
            offset+=chars;
        }
    }
