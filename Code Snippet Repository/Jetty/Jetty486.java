    @Test
    public void test_BasicAuthentication_WithWrongPassword() throws Exception
    {
        startBasic(new EmptyServerHandler());

        AuthenticationStore authenticationStore = client.getAuthenticationStore();
        URI uri = URI.create(scheme + "://localhost:" + connector.getLocalPort());
        BasicAuthentication authentication = new BasicAuthentication(uri, realm, "basic", "wrong");
        authenticationStore.addAuthentication(authentication);

        Request request = client.newRequest("localhost", connector.getLocalPort()).scheme(scheme).path("/secure");
        ContentResponse response = request.timeout(5, TimeUnit.SECONDS).send();
        Assert.assertNotNull(response);
        Assert.assertEquals(401, response.getStatus());
    }
