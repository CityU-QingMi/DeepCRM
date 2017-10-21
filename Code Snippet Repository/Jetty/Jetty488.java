    @Test
    public void test_PreemptedAuthentication() throws Exception
    {
        startBasic(new EmptyServerHandler());

        AuthenticationStore authenticationStore = client.getAuthenticationStore();
        URI uri = URI.create(scheme + "://localhost:" + connector.getLocalPort());
        authenticationStore.addAuthenticationResult(new BasicAuthentication.BasicResult(uri, "basic", "basic"));

        AtomicInteger requests = new AtomicInteger();
        client.getRequestListeners().add(new Request.Listener.Adapter()
        {
            @Override
            public void onSuccess(Request request)
            {
                requests.incrementAndGet();
            }
        });

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .path("/secure")
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(1, requests.get());
    }
