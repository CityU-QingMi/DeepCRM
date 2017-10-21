        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException
        {
            String action = request.getParameter("action");
            if ("init".equals(action))
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("test", "test");
                originalId = session.getId();
            }
            else if ("test".equals(action))
            {
                HttpSession session = request.getSession(true);
                assertTrue(session != null);
                assertTrue(!originalId.equals(session.getId()));
            }
            else if ("notexpired".equals(action))
            {
                HttpSession session = request.getSession(false);
                assertTrue(session != null);
                assertTrue(originalId.equals(session.getId()));
            }

        }
