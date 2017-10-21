    private static void parseResponse(Input in, HttpParser parser, Response r) throws IOException
    {
        ByteBuffer buffer = in.getBuffer();
        
        while(true)
        {
            if (BufferUtil.hasContent(buffer))
                if (parser.parseNext(buffer))
                    break;
            int len=in.fillBuffer();
            if (len==0)
                break;
            if (len<=0)
            {
                parser.atEOF();
                parser.parseNext(buffer);
                break;
            }
        }
    }
