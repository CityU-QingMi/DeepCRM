    @SuppressWarnings("")
    @Test
    public void testLegacyDecorator() throws Exception
    {
        ServletContextHandler context = new ServletContextHandler();
        context.addDecorator(new DummyLegacyDecorator());
        _server.setHandler(context);
        
        context.addServlet(DecoratedObjectFactoryServlet.class, "/objfactory/*");
        _server.start();

        String response= _connector.getResponse("GET /objfactory/ HTTP/1.0\r\n\r\n");
        assertThat("Response status code", response, containsString("200 OK"));
        
        String expected = String.format("Attribute[%s] = %s", DecoratedObjectFactory.ATTR, DecoratedObjectFactory.class.getName());
        assertThat("Has context attribute", response, containsString(expected));
        
        assertThat("Decorators size", response, containsString("Decorators.size = [2]"));
        
        expected = String.format("decorator[] = %s", DummyLegacyDecorator.class.getName());
        assertThat("Specific Legacy Decorator", response, containsString(expected));
    }
