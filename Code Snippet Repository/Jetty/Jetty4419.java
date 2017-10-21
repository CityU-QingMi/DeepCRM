    @Test
    public void testBodyAlreadyConsumed()
    throws Exception
    {
        tester.addServlet(NullServlet.class,"/null");    
        
        FilterHolder holder = new FilterHolder();
        holder.setName("reader");
        holder.setFilter(new ReadAllFilter());
        tester.getContext().getServletHandler().addFilter(holder);
        FilterMapping mapping = new FilterMapping();
        mapping.setFilterName("reader");
        mapping.setPathSpec("/*");
        tester.getContext().getServletHandler().prependFilterMapping(mapping);
        String boundary="XyXyXy";
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;
        
        request.setMethod("POST");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setURI("/context/null");
        request.setHeader("Content-Type","multipart/form-data; boundary="+boundary);
        request.setContent("How now brown cow");

        try(StacklessLogging stackless = new StacklessLogging(ServletHandler.class))
        {
            response = HttpTester.parseResponse(tester.getResponses(request.generate()));
            assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        }
    }
