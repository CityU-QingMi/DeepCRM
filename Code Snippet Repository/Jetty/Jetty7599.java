        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException
        {
            String action = request.getParameter("action");
            if ("init".equals(action))
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("value", new Integer(1));
                originalId = session.getId();
                Session s = (Session)session;
                try (Lock lock = s.lock())
                {
                    assertTrue(s.isResident());
                }
                _session = s;
            }
            else if ("test".equals(action))
            {
                HttpSession session = request.getSession(false);
                assertTrue(session != null);
                assertTrue(originalId.equals(session.getId()));
                Session s = (Session)session;
                try (Lock lock = s.lock();)
                {
                   assertTrue(s.isResident());
                }
                Integer v = (Integer)session.getAttribute("value");
                session.setAttribute("value", new Integer(v.intValue()+1));
                _session = session;
            }
            else if ("testfail".equals(action))
            {
                HttpSession session = request.getSession(false);
                assertTrue(session == null);
                _session = session;
            }
        }
