        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            // include doesn't hide forward
            assertEquals( "/context/ForwardServlet/forwardpath", request.getAttribute(Dispatcher.FORWARD_REQUEST_URI));
            assertEquals( "/context", request.getAttribute(Dispatcher.FORWARD_CONTEXT_PATH) );
            assertEquals( "/ForwardServlet", request.getAttribute(Dispatcher.FORWARD_SERVLET_PATH));
            assertEquals( "/forwardpath", request.getAttribute(Dispatcher.FORWARD_PATH_INFO));
            assertEquals( "do=include", request.getAttribute(Dispatcher.FORWARD_QUERY_STRING) );

            assertEquals( "/context/AssertForwardIncludeServlet/assertpath", request.getAttribute(Dispatcher.INCLUDE_REQUEST_URI));
            assertEquals( "/context", request.getAttribute(Dispatcher.INCLUDE_CONTEXT_PATH) );
            assertEquals( "/AssertForwardIncludeServlet", request.getAttribute(Dispatcher.INCLUDE_SERVLET_PATH));
            assertEquals( "/assertpath", request.getAttribute(Dispatcher.INCLUDE_PATH_INFO));
            assertEquals( "do=end", request.getAttribute(Dispatcher.INCLUDE_QUERY_STRING));

            List expectedAttributeNames = Arrays.asList(Dispatcher.FORWARD_REQUEST_URI, Dispatcher.FORWARD_CONTEXT_PATH, Dispatcher.FORWARD_SERVLET_PATH,
                    Dispatcher.FORWARD_PATH_INFO, Dispatcher.FORWARD_QUERY_STRING,
                    Dispatcher.INCLUDE_REQUEST_URI, Dispatcher.INCLUDE_CONTEXT_PATH, Dispatcher.INCLUDE_SERVLET_PATH,
                    Dispatcher.INCLUDE_PATH_INFO, Dispatcher.INCLUDE_QUERY_STRING);
            List requestAttributeNames = Collections.list(request.getAttributeNames());
            assertTrue(requestAttributeNames.containsAll(expectedAttributeNames));

            assertEquals("/includepath", request.getPathInfo());
            assertEquals(null, request.getPathTranslated());
            assertEquals("do=assertforwardinclude", request.getQueryString());
            assertEquals("/context/IncludeServlet/includepath", request.getRequestURI());
            assertEquals("/context", request.getContextPath());
            assertEquals("/IncludeServlet", request.getServletPath());

            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
        }
