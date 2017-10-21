        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException
        {
            String action = request.getParameter("action");
            if ("init".equals(action))
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("aaa", new Integer(0));
            }
            else if ("test".equals(action))
            {
                HttpSession session = request.getSession(false);
               assertNotNull(session);
               Integer count = (Integer)session.getAttribute("aaa");
               assertNotNull(count);
               session.setAttribute("aaa", new Integer(count.intValue()+1));
            }
        }
