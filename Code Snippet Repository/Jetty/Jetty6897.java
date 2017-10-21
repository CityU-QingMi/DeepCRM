    @Override
    public HttpResponse readResponseHeader() throws IOException
    {
        HttpResponse response = new HttpResponse();
        HttpResponseHeaderParser respParser = new HttpResponseHeaderParser(response);

        byte buf[] = new byte[512];

        while (!eof)
        {
            int available = in.available();
            int len = in.read(buf,0,Math.min(available,buf.length));
            if (len < 0)
            {
                eof = true;
                break;
            }
            else if (len > 0)
            {
                ByteBuffer bbuf = ByteBuffer.wrap(buf,0,len);
                if (LOG.isDebugEnabled())
                {
                    LOG.debug("Read {} bytes: {}",len,BufferUtil.toDetailString(bbuf));
                }
                if (respParser.parse(bbuf) != null)
                {
                    break;
                }
            }
        }

        remainingBuffer = response.getRemainingBuffer();

        return response;
    }
