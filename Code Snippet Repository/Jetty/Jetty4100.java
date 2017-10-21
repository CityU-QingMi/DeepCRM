        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            byte[] cp1251_bytes = TypeUtil.fromHexString("d2e5ecefe5f0e0f2f3f0e0");
            String expectedCP1251String = new String(cp1251_bytes, "cp1251");
            
            assertEquals( "/context/ForwardServlet", request.getAttribute(Dispatcher.FORWARD_REQUEST_URI));
            assertEquals( "/context", request.getAttribute(Dispatcher.FORWARD_CONTEXT_PATH) );
            assertEquals( "/ForwardServlet", request.getAttribute(Dispatcher.FORWARD_SERVLET_PATH));
            assertEquals( null, request.getAttribute(Dispatcher.FORWARD_PATH_INFO));
            assertEquals( "do=assertforward&foreign=%d2%e5%ec%ef%e5%f0%e0%f2%f3%f0%e0&test=1", request.getAttribute(Dispatcher.FORWARD_QUERY_STRING) );

            List<String> expectedAttributeNames = Arrays.asList(Dispatcher.FORWARD_REQUEST_URI, Dispatcher.FORWARD_CONTEXT_PATH,
                    Dispatcher.FORWARD_SERVLET_PATH, Dispatcher.FORWARD_QUERY_STRING);
            List<String> requestAttributeNames = Collections.list(request.getAttributeNames());
            assertTrue(requestAttributeNames.containsAll(expectedAttributeNames));

            assertEquals(null, request.getPathInfo());
            assertEquals(null, request.getPathTranslated());
            
            UrlEncoded query = new UrlEncoded();
            query.decode(request.getQueryString());
            assertThat(query.getString("do"), is("end"));
            
            // Russian for "selected=Temperature"
            UrlEncoded q2=new UrlEncoded();
            q2.decode(query.getString("else"));
            String russian = q2.encode();
            assertThat(russian, is("%D0%B2%D1%8B%D0%B1%D1%80%D0%B0%D0%BD%D0%BE=%D0%A2%D0%B5%D0%BC%D0%BF%D0%B5%D1%80%D0%B0%D1%82%D1%83%D1%80%D0%B0"));
            assertThat(query.getString("test"), is("1"));
            assertThat(query.containsKey("foreign"), is(true));
            
            String[] vals = request.getParameterValues("foreign");
            assertTrue(vals!=null);
            assertEquals(1, vals.length);
            assertEquals(expectedCP1251String, vals[0]);
            
            assertEquals("/context/AssertForwardServlet", request.getRequestURI());
            assertEquals("/context", request.getContextPath());
            assertEquals("/AssertForwardServlet", request.getServletPath());

            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
        }
