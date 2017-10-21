    @Test
    public void testPushedResourceRedirect() throws Exception
    {
        Random random = new Random();
        byte[] pushBytes = new byte[512];
        random.nextBytes(pushBytes);

        String oldPath = "/old";
        String newPath = "/new";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                if (target.equals(oldPath))
                    response.sendRedirect(newPath);
                else if (target.equals(newPath))
                    response.getOutputStream().write(pushBytes);
                else
                    baseRequest.getPushBuilder().path(oldPath).push();
            }
        });

        CountDownLatch latch = new CountDownLatch(1);
        HttpRequest request = (HttpRequest)client.newRequest("localhost", connector.getLocalPort());
        ContentResponse response = request
                .pushListener((mainRequest, pushedRequest) -> new BufferingResponseListener()
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        Assert.assertTrue(result.isSucceeded());
                        Assert.assertEquals(oldPath, pushedRequest.getPath());
                        Assert.assertEquals(newPath, result.getRequest().getPath());
                        Assert.assertArrayEquals(pushBytes, getContent());
                        latch.countDown();
                    }
                })
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
