    @Test
    public void testRestrictedWithoutAuthenticatorAndMethod() throws Exception
    {
        Constraint constraint0 = new Constraint();
        constraint0.setAuthenticate(true);
        constraint0.setRoles(new String[] { "admin" } );
        constraint0.setName("restricted");
        ConstraintMapping mapping0 = new ConstraintMapping();
        mapping0.setPathSpec("/restricted/*");
        mapping0.setMethod("GET");
        mapping0.setConstraint(constraint0);

        _security.setConstraintMappings(Arrays.asList(new ConstraintMapping[]
                                                                            {
                mapping0
                                                                            }));
        _server.start();

        String response;

        response = _connector.getResponse("GET /ctx/restricted/info HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.containsString("HTTP/1.1 403 Forbidden"));

        response = _connectorS.getResponse("GET /ctx/restricted/info HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.containsString("HTTP/1.1 403 Forbidden"));

        response = _connector.getResponse("GET /ctx/restricted/info HTTP/1.0\r\nAuthorization: Basic YWRtaW46cGFzc3dvcmQ=\r\n\r\n");
        Assert.assertThat(response, Matchers.containsString("HTTP/1.1 403 Forbidden"));

        response = _connectorS.getResponse("GET /ctx/restricted/info HTTP/1.0\r\nAuthorization: Basic YWRtaW46cGFzc3dvcmQ=\r\n\r\n");
        Assert.assertThat(response, Matchers.containsString("HTTP/1.1 403 Forbidden"));

    }
