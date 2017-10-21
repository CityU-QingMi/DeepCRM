    public  List<HttpTester.Response> readResponses(Socket sock) throws IOException
    {
       List<HttpTester.Response> list = new ArrayList<>();
       String r = readRaw(sock);
       ByteBuffer buffer = BufferUtil.toBuffer(r);
       while(BufferUtil.hasContent(buffer))
       {
           HttpTester.Response response = HttpTester.parseResponse(buffer);
           if (response == null)
               break;
           list.add(response);
       }
       return list;
    }
