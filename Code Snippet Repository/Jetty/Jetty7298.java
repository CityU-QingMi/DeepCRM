    private void testAsyncWriteThrows(Throwable throwable) throws Exception
    {
        CountDownLatch latch = new CountDownLatch(1);
        start(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                assertScope();
                AsyncContext asyncContext = request.startAsync(request, response);
                response.getOutputStream().setWriteListener(new WriteListener()
                {
                    @Override
                    public void onWritePossible() throws IOException
                    {
                        assertScope();
                        if (throwable instanceof RuntimeException)
                            throw (RuntimeException)throwable;
                        if (throwable instanceof Error)
                            throw (Error)throwable;
                        throw new IOException(throwable);
                    }

                    @Override
                    public void onError(Throwable t)
                    {
                        assertScope();
                        latch.countDown();
                        response.setStatus(500);
                        asyncContext.complete();
                        Assert.assertSame(throwable, t);
                    }
                });
            }
        });

        ContentResponse response = client.newRequest(newURI())
                .path(servletPath)
                .timeout(5, TimeUnit.SECONDS)
                .send();

        assertTrue(latch.await(5, TimeUnit.SECONDS));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR_500, response.getStatus());
    }
