    @Test
    public void testBufferedReadsResetStreamIdleTimeout() throws Exception
    {
        int bufferSize = 8192;
        long delay = 1000;
        start(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                ServletInputStream input = request.getInputStream();
                byte[] buffer = new byte[bufferSize];
                while (true)
                {
                    int read = input.read(buffer);
                    Log.getLogger(IdleTimeoutTest.class).info("Read {} bytes", read);
                    if (read < 0)
                        break;
                    sleep(delay);
                }
            }
        });
        connector.setIdleTimeout(2 * delay);

        Session session = newClient(new Session.Listener.Adapter());
        MetaData.Request metaData = newRequest("POST", new HttpFields());
        HeadersFrame requestFrame = new HeadersFrame(metaData, null, false);
        FuturePromise<Stream> promise = new FuturePromise<>();
        CountDownLatch latch = new CountDownLatch(1);
        session.newStream(requestFrame, promise, new Stream.Listener.Adapter()
        {
            @Override
            public void onHeaders(Stream stream, HeadersFrame frame)
            {
                if (frame.isEndStream())
                    latch.countDown();
            }
        });
        Stream stream = promise.get(5, TimeUnit.SECONDS);

        // Send data larger than the flow control window.
        // The client will send bytes up to the flow control window immediately
        // and they will be buffered by the server; the Servlet will consume them slowly.
        // Servlet reads should reset the idle timeout.
        int contentLength = FlowControlStrategy.DEFAULT_WINDOW_SIZE + 1;
        ByteBuffer data = ByteBuffer.allocate(contentLength);
        stream.data(new DataFrame(stream.getId(), data, true), Callback.NOOP);

        Assert.assertTrue(latch.await(2 * (contentLength / bufferSize + 1) * delay, TimeUnit.MILLISECONDS));
    }
