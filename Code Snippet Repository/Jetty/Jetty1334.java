    private String getProxyField(ByteBuffer buffer)
    {
        _string.setLength(0);
        _length=0;

        while (buffer.hasRemaining())
        {
            // process each character
            byte ch=next(buffer);
            if (ch<=' ')
                return _string.toString();
            _string.append((char)ch);
        }
        throw new BadMessageException();
    }
