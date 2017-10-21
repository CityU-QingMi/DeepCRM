    @Test
    public void testPushedResourceCancelled() throws Exception
    {
        String pushPath = "/secondary";
        CountDownLatch latch = new CountDownLatch(1);
        start(new ServerSessionListener.Adapter()
        {
            @Override
            public Stream.Listener onNewStream(Stream stream, HeadersFrame frame)
            {
                HttpURI pushURI = new HttpURI("http://localhost:" + connector.getLocalPort() + pushPath);
                MetaData.Request pushRequest = new MetaData.Request(HttpMethod.GET.asString(), pushURI, HttpVersion.HTTP_2, new HttpFields());
                stream.push(new PushPromiseFrame(stream.getId(), 0, pushRequest), new Promise.Adapter<Stream>()
                {
                    @Override
                    public void succeeded(Stream pushStream)
                    {
                        // Just send the normal response and wait for the reset.
                        MetaData.Response response = new MetaData.Response(HttpVersion.HTTP_2, HttpStatus.OK_200, new HttpFields());
                        stream.headers(new HeadersFrame(stream.getId(), response, null, true), Callback.NOOP);
                    }
                }, new Stream.Listener.Adapter()
                {
                    @Override
                    public void onReset(Stream stream, ResetFrame frame)
                    {
                        latch.countDown();
                    }
                });
                return null;
            }
        });

        HttpRequest request = (HttpRequest)client.newRequest("localhost", connector.getLocalPort());
        ContentResponse response = request
                .pushListener((mainRequest, pushedRequest) -> null)
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
