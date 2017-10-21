        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            String action = request.getParameter("action");
            if ("create".equals(action))
            {
                HttpSession session = request.getSession(true);
                assertTrue(session.isNew());
                id = session.getId();
                return;
            }
            if ("update".equals(action))
            {
                HttpSession session = request.getSession(false);
                assertNotNull(session);
                session.setAttribute("foo", "bar");
                return;
            }
        }
