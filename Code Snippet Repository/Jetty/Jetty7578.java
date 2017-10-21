        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException
        {
            String action = request.getParameter("action");
            if ("init".equals(action))
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("identity", "session1");
                session.setMaxInactiveInterval(-1); //don't let this session expire, we want to explicitly invalidate it
            }
            else if ("test".equals(action))
            {
                HttpSession session = request.getSession(false);
                if (session != null)
                {
                    String oldId = session.getId();

                    //invalidate existing session
                    session.invalidate();

                    //now try to access the invalid session
                    try
                    {
                        session.getAttribute("identity");
                        fail("Session should be invalid");
                    }
                    catch (IllegalStateException e)
                    {
                        assertNotNull(e.getMessage());
                        assertTrue(e.getMessage().contains("id"));
                    }

                    //now make a new session
                    session = request.getSession(true);
                    String newId = session.getId();
                    assertTrue(!newId.equals(oldId));
                    assertTrue (session.getAttribute("identity")==null);
                    session.setAttribute("identity", "session2");
                    session.setAttribute("listener", listener);
                }
                else
                    fail("Session already missing");
            }
        }
