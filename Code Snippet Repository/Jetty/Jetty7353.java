    @Test(expected = ExecutionException.class)
    public void testInputStreamContentProviderThrowingWhileReading() throws Exception
    {
        start(new AbstractHandler.ErrorDispatchHandler()
        {
            @Override
            public void doNonErrorHandle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                IO.copy(request.getInputStream(), response.getOutputStream());
            }
        });

        final byte[] data = new byte[]{0, 1, 2, 3};
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(getScheme())
                .content(new InputStreamContentProvider(new InputStream()
                {
                    private int index = 0;

                    @Override
                    public int read() throws IOException
                    {
                        // Will eventually throw ArrayIndexOutOfBounds
                        return data[index++];
                    }
                }, data.length / 2))
                .timeout(5, TimeUnit.SECONDS)
                .send();
    }
