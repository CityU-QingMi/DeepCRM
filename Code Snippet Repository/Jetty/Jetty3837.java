    private String response(InputStream input) throws IOException
    {
        Utf8StringBuilder buffer = new Utf8StringBuilder();
        int crlfs = 0;
        while (true)
        {
            int read = input.read();
            Assert.assertTrue(read >= 0);
            buffer.append((byte)read);
            crlfs = (read == '\r' || read == '\n') ? crlfs + 1 : 0;
            if (crlfs == 4)
                break;
        }
        return buffer.toString();
    }
