    @Test
    public void testHead() throws Exception
    {
        String responsePOST=connector.getResponse("POST /R1 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "Connection: close\r\n"+
            "\r\n");

        String responseHEAD=connector.getResponse("HEAD /R1 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "Connection: close\r\n"+
            "\r\n");

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
