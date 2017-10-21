        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            String action = request.getParameter("action");
            
            if ("create".equals(action))
            {
                HttpSession session = request.getSession(true);
                return;
            }
            
            if ("setA".equals(action))
            {
                HttpSession session = request.getSession(false);
                if (session == null)
                    throw new ServletException("Session is null for action=change");

                session.setAttribute(THE_NAME, A_VALUE);
                return;
            }
            
            if ("setB".equals(action))
            {
                HttpSession session = request.getSession(false);
                if (session == null)
                    throw new ServletException("Session does not exist");
                session.setAttribute(THE_NAME, B_VALUE);
                return;
            }
        }
