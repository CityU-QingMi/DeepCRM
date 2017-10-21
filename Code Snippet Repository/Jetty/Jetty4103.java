        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            // forward hides include
            assertEquals( null, request.getAttribute(Dispatcher.INCLUDE_REQUEST_URI));
            assertEquals( null, request.getAttribute(Dispatcher.INCLUDE_CONTEXT_PATH) );
            assertEquals( null, request.getAttribute(Dispatcher.INCLUDE_SERVLET_PATH));
            assertEquals( null, request.getAttribute(Dispatcher.INCLUDE_PATH_INFO));
            assertEquals( null, request.getAttribute(Dispatcher.INCLUDE_QUERY_STRING));

            assertEquals( "/context/IncludeServlet/includepath", request.getAttribute(Dispatcher.FORWARD_REQUEST_URI));
            assertEquals( "/context", request.getAttribute(Dispatcher.FORWARD_CONTEXT_PATH) );
            assertEquals( "/IncludeServlet", request.getAttribute(Dispatcher.FORWARD_SERVLET_PATH));
            assertEquals( "/includepath", request.getAttribute(Dispatcher.FORWARD_PATH_INFO));
            assertEquals( "do=forward", request.getAttribute(Dispatcher.FORWARD_QUERY_STRING) );

            List expectedAttributeNames = Arrays.asList(Dispatcher.FORWARD_REQUEST_URI, Dispatcher.FORWARD_CONTEXT_PATH, Dispatcher.FORWARD_SERVLET_PATH,
                    Dispatcher.FORWARD_PATH_INFO, Dispatcher.FORWARD_QUERY_STRING);
            List requestAttributeNames = Collections.list(request.getAttributeNames());
            assertTrue(requestAttributeNames.containsAll(expectedAttributeNames));

            assertEquals("/assertpath", request.getPathInfo());
            assertEquals(null, request.getPathTranslated());
            assertEquals("do=end", request.getQueryString());
            assertEquals("/context/AssertIncludeForwardServlet/assertpath", request.getRequestURI());
            assertEquals("/context", request.getContextPath());
            assertEquals("/AssertIncludeForwardServlet", request.getServletPath());

            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
        }
