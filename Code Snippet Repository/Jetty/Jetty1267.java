    @Test
    public void testNonISOHeader() throws Exception
    {
        try (StacklessLogging stackless = new StacklessLogging(HttpChannel.class))
        {
            startServer(new HttpServlet()
            {
                @Override
                protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
                {
                    // Invalid header name, the connection must be closed.
                    response.setHeader("Euro_(\u20AC)", "42");
                }
            });

            ByteBufferPool.Lease lease = new ByteBufferPool.Lease(byteBufferPool);
            generator.control(lease, new PrefaceFrame());
            generator.control(lease, new SettingsFrame(new HashMap<>(), false));
            MetaData.Request metaData = newRequest("GET", new HttpFields());
            generator.control(lease, new HeadersFrame(1, metaData, null, true));

            try (Socket client = new Socket("localhost", connector.getLocalPort()))
            {
                OutputStream output = client.getOutputStream();
                for (ByteBuffer buffer : lease.getByteBuffers())
                    output.write(BufferUtil.toArray(buffer));
                output.flush();

                Parser parser = new Parser(byteBufferPool, new Parser.Listener.Adapter(), 4096, 8192);
                boolean closed = parseResponse(client, parser);

                Assert.assertTrue(closed);
            }
        }
    }
