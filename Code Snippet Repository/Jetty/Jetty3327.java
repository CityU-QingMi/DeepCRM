    @Test
    public void testHeadChunked() throws Exception
    {
        String responsePOST=connector.getResponse("POST /R1?no-content-length=true HTTP/1.1\r\n"+
                "Host: localhost\r\n"+
                "\r\n",false,1,TimeUnit.SECONDS);

        String responseHEAD=connector.getResponse("HEAD /R1?no-content-length=true HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "\r\n",true,1,TimeUnit.SECONDS);

        String postLine;
        boolean postDate=false;
        Set<String> postHeaders = new HashSet<>();
        try(BufferedReader in = new BufferedReader(new StringReader(responsePOST)))
        {
            postLine = in.readLine();
            String line=in.readLine();
            while (line!=null && line.length()>0)
            {
                if (line.startsWith("Date:"))
                    postDate=true;
                else
                    postHeaders.add(line);
                line=in.readLine();
            }
        }
        String headLine;
        boolean headDate=false;
        Set<String> headHeaders = new HashSet<>();
        try(BufferedReader in = new BufferedReader(new StringReader(responseHEAD)))
        {
            headLine = in.readLine();
            String line=in.readLine();
            while (line!=null && line.length()>0)
            {
                if (line.startsWith("Date:"))
                    headDate=true;
                else
                    headHeaders.add(line);
                line=in.readLine();
            }
        }

        assertThat(postLine,equalTo(headLine));
        assertThat(postDate,equalTo(headDate));
        assertTrue(postHeaders.equals(headHeaders));
    }
