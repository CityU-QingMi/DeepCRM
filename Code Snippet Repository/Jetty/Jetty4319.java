    public String readResponse(HttpTester.Response response) throws IOException, UnsupportedEncodingException
    {
        String actual = null;
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try
        {
            byte[] content = response.getContentBytes();
            if (content != null)
                actual = new String(response.getContentBytes(),encoding);
            else
                actual = "";
        }
        finally
        {
            IO.close(out);
            IO.close(in);
        }
        return actual;
    }
