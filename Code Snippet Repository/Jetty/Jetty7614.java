        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException
        {
            String action = request.getParameter("action");
         
            if ("init".equals(action))
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("foo", listener);
                assertNotNull(session);

            }
            else if ("test".equals(action))
            {
                HttpSession session = request.getSession(false);
                assertNotNull(session);
                
                //invalidate existing session
                session.invalidate();
            }
        }
