    @Test
    public void testInitOrder() throws Exception
    {
        ServletContextHandler context = new ServletContextHandler();
        ServletHolder holder0 = context.addServlet(TestServlet.class,"/test0");
        ServletHolder holder1 = context.addServlet(TestServlet.class,"/test1");
        ServletHolder holder2 = context.addServlet(TestServlet.class,"/test2");
        
        holder1.setInitOrder(1);
        holder2.setInitOrder(2);
        
        context.setContextPath("/");
        _server.setHandler(context);
        _server.start();
        
        assertEquals(2,__testServlets.get());
        
        String response =_connector.getResponse("GET /test1 HTTP/1.0\r\n\r\n");
        Assert.assertThat(response,Matchers.containsString("200 OK"));
        
        assertEquals(2,__testServlets.get());
        
        response =_connector.getResponse("GET /test2 HTTP/1.0\r\n\r\n");
        Assert.assertThat(response,containsString("200 OK"));
        
        assertEquals(2,__testServlets.get());
        
        assertThat(holder0.getServletInstance(),nullValue());
        response =_connector.getResponse("GET /test0 HTTP/1.0\r\n\r\n");
        assertThat(response,containsString("200 OK"));
        assertEquals(3,__testServlets.get());
        assertThat(holder0.getServletInstance(),notNullValue(Servlet.class));

        _server.stop();
        assertEquals(0,__testServlets.get());
        
        holder0.setInitOrder(0);
        _server.start();
        assertEquals(3,__testServlets.get());
        assertThat(holder0.getServletInstance(),notNullValue(Servlet.class));
        _server.stop();
        assertEquals(0,__testServlets.get());
        
    }
