    @Test
    public void testConfidentialWithNoRolesSetAndMethodRestriction() throws Exception
    {
        Constraint constraint0 = new Constraint();
        constraint0.setName("confid");
        constraint0.setDataConstraint(Constraint.DC_CONFIDENTIAL);
        ConstraintMapping mapping0 = new ConstraintMapping();
        mapping0.setPathSpec("/confid/*");
        mapping0.setMethod(HttpMethod.POST.asString());
        mapping0.setConstraint(constraint0);

        _security.setConstraintMappings(Arrays.asList(new ConstraintMapping[]
                                                                            {
                mapping0
                                                                            }));

        _server.start();

        String response;

        response = _connector.getResponse("GET /ctx/confid/info HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.containsString("HTTP/1.1 404 Not Found"));

        response = _connectorS.getResponse("GET /ctx/confid/info HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.containsString("HTTP/1.1 404 Not Found"));

        response = _connector.getResponse("POST /ctx/confid/info HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.containsString("HTTP/1.1 302 Found"));

        response = _connectorS.getResponse("POST /ctx/confid/info HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.containsString("HTTP/1.1 404 Not Found"));

    }
