    private static void putContentLength(ByteBuffer header,long contentLength)
    {
        if (contentLength==0)
            header.put(CONTENT_LENGTH_0);
        else
        {
            header.put(HttpHeader.CONTENT_LENGTH.getBytesColonSpace());
            BufferUtil.putDecLong(header, contentLength);
            header.put(HttpTokens.CRLF);
        }
    }
