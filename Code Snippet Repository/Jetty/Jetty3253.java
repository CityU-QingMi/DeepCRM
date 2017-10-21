    private void testRequest(String headers, RequestValidator requestValidator) throws Exception
    {
        Server server = new Server();
        // Activate reverse proxy headers checking
        HttpConnectionFactory http = new HttpConnectionFactory();
        http.getHttpConfiguration().addCustomizer(new ForwardedRequestCustomizer());

        LocalConnector connector = new LocalConnector(server,http);

        server.setConnectors(new Connector[] {connector});
        ValidationHandler validationHandler = new ValidationHandler(requestValidator);
        server.setHandler(validationHandler);

        try
        {
            server.start();
            connector.getResponse("GET / HTTP/1.1\r\n" +"Connection: close\r\n" + headers + "\r\n\r\n");
            Error error = validationHandler.getError();

            if (error != null)
            {
                throw error;
            }
        }
        finally
        {
            server.stop();
        }
    }
