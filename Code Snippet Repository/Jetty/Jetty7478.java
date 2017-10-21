    public static List<HttpTester.Response> readResponses(String string) throws IOException
    {
        List<HttpTester.Response> list = new ArrayList<>();

        ByteBuffer buffer = BufferUtil.toBuffer(string);
        while(BufferUtil.hasContent(buffer))
        {
            HttpTester.Response response = HttpTester.parseResponse(buffer);
            if (response == null)
                break;
            list.add(response);
        }
        return list;
    }
