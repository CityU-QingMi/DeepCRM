        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {


            String action = request.getParameter("action");
            if ("create".equals(action))
            {
                request.getSession(true);
                return;
            }
            
            HttpSession session = request.getSession(false);
            if ("reenter".equals(action))
            {
                if (session == null)
                    session = request.getSession(true);
                int port = Integer.parseInt(request.getParameter("port"));
                String path = request.getParameter("path");

                // We want to make another request
                // while this request is still pending, to see if the locking is
                // fine grained (per session at least).
                try
                {
                    HttpClient client = new HttpClient();
                    client.start();
                    try
                    {
                        ContentResponse resp = client.GET("http://localhost:" + port + path + ";jsessionid="+session.getId()+"?action=none");
                        assertEquals(HttpServletResponse.SC_OK,resp.getStatus());
                        assertEquals("true",session.getAttribute("reentrant"));
                    }
                    finally
                    {
                        client.stop();
                    }
                }
                catch (Exception x)
                {
                    throw new ServletException(x);
                }
            }
            else
            {
                assertTrue(session!=null);
                session.setAttribute("reentrant","true");
            }
        }
