        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException
        {
            String action = request.getParameter("action");

            if ("test".equals(action))
            {
               HttpSession session = request.getSession(false);
               assertNull(session);
            }
        }
