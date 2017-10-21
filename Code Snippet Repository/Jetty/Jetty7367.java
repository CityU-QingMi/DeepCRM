    @Test
    public void testClientManyWritesSlowServer() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);

                long sleep = 1024;
                long total = 0;
                ServletInputStream input = request.getInputStream();
                byte[] buffer = new byte[1024];
                while (true)
                {
                    int read = input.read(buffer);
                    if (read < 0)
                        break;
                    total += read;
                    if (total >= sleep)
                    {
                        sleep(250);
                        sleep += 256;
                    }
                }

                response.getOutputStream().print(total);
            }
        });

        int chunks = 256;
        int chunkSize = 16;
        byte[][] bytes = IntStream.range(0, chunks).mapToObj(x -> new byte[chunkSize]).toArray(byte[][]::new);
        BytesContentProvider contentProvider = new BytesContentProvider("application/octet-stream", bytes);
        ContentResponse response = client.newRequest(newURI())
                .method(HttpMethod.POST)
                .content(contentProvider)
                .timeout(15, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
        Assert.assertEquals(chunks * chunkSize, Integer.parseInt(response.getContentAsString()));
    }
