        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            String action = request.getParameter("action");
            if ("create".equals(action))
            {
                HttpSession session = request.getSession(true);
                assertTrue(session.isNew());
                id = session.getId();
            }
            else if ("old-create".equals(action))
            {
                HttpSession s = request.getSession(false);
                assertNull(s);
                s = request.getSession(true);
                assertNotNull(s);
                assertFalse(s.getId().equals(id));
            }
            else if ("old-test".equals(action))
            {
                HttpSession s = request.getSession(false);
                assertNotNull(s);
                assertTrue(s.getId().equals(id));
            }
            else
            {
                assertTrue(false);
            }
        }
