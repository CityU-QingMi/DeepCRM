    public HttpTester.Response readAvailable(Socket sock) throws IOException
    {
        
        String rawResponse = readRawAvailable(sock);
        if (StringUtil.isBlank(rawResponse))
        {
            return null;
        }
        return HttpTester.parseResponse(rawResponse);
    }
