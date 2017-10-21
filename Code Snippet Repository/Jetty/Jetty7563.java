        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            String action = request.getParameter("action");
            if ("init".equals(action))
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("value", 0);
            }
            else if ("increment".equals(action))
            {
                HttpSession session = request.getSession(false);
                int value = (Integer)session.getAttribute("value");
                session.setAttribute("value", value + 1);
            }
            else if ("invalidate".equals(action))
            {
                HttpSession session = request.getSession(false);
                session.invalidate();
                
                try
                {
                    session.invalidate();
                    fail("Session should be invalid");
                    
                }
                catch (IllegalStateException e)
                {
                    //expected
                }
                
            }
            else if ("test".equals(action))
            {
                HttpSession session = request.getSession(false);
                assertEquals(null,session);
            }
        }
