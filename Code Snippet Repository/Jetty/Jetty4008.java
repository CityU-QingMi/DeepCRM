    @Test
    public void testAsync2() throws Exception
    {
        StringBuilder request = new StringBuilder(512);
        request.append("GET /ctx/path2/info HTTP/1.1\r\n")
        .append("Host: localhost\r\n")
        .append("Connection: close\r\n")
        .append("\r\n");
        
        int port=_port;
        List<String> list = new ArrayList<>();
        try (Socket socket = new Socket("localhost",port))
        {
            socket.setSoTimeout(1000000);
            OutputStream out = socket.getOutputStream();
            out.write(request.toString().getBytes(StandardCharsets.ISO_8859_1));
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()),102400);
            
            // response line
            String line = in.readLine();
            LOG.debug("response-line: "+line);
            Assert.assertThat(line,startsWith("HTTP/1.1 200 OK"));
            
            // Skip headers
            while (line!=null)
            {
                line = in.readLine();
                LOG.debug("header-line: "+line);
                if (line.length()==0)
                    break;
            }

            // Get body slowly
            while (true)
            {
                line = in.readLine();
                LOG.debug("body: "+line);
                if (line==null)
                    break;
                list.add(line);
            }
        }

        Assert.assertEquals(list.get(0),"data");
        Assert.assertTrue(_servlet2.completed.await(5, TimeUnit.SECONDS));
    }
