    protected boolean parseResponse(Socket client, Parser parser, long timeout) throws IOException
    {
        byte[] buffer = new byte[2048];
        InputStream input = client.getInputStream();
        client.setSoTimeout((int)timeout);
        while (true)
        {
            try
            {
                int read = input.read(buffer);
                if (read < 0)
                    return true;
                parser.parse(ByteBuffer.wrap(buffer, 0, read));
                if (client.isClosed())
                    return true;
            }
            catch (SocketTimeoutException x)
            {
                return false;
            }
        }
    }
