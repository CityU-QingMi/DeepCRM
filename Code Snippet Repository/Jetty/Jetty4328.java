    @Test
    public void testGzip() throws Exception
    {
        // generated and parsed test

        ByteBuffer request=BufferUtil.toBuffer(
            "GET /context/file.txt HTTP/1.0\r\n"+
            "Host: tester\r\n"+
            "Accept-Encoding: "+compressionType+"\r\n"+
            "\r\n");


        HttpTester.Response response=HttpTester.parseResponse(tester.getResponses(request));

        assertEquals(HttpServletResponse.SC_OK,response.getStatus());
        assertEquals(compressionType,response.get("Content-Encoding"));

        InputStream testIn = null;
        ByteArrayInputStream compressedResponseStream = new ByteArrayInputStream(response.getContentBytes());
        if (compressionType.equals(GzipHandler.GZIP))
        {
            testIn = new GZIPInputStream(compressedResponseStream);
        }
        else if (compressionType.equals(GzipHandler.DEFLATE))
        {
            testIn = new InflaterInputStream(compressedResponseStream, new Inflater(true));
        }
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        IO.copy(testIn,testOut);

        assertEquals(__content, testOut.toString("ISO8859_1"));
    }
