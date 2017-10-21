        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            String action = request.getParameter("action");
            if ("create".equals(action))
            {
                request.getSession(true);
            }
            else if ("delete".equals(action))
            {
                HttpSession s = request.getSession(false);
                assertNotNull(s);
                s.invalidate();
                s = request.getSession(false);
                assertNull(s);
            }
            else
            {
               HttpSession s = request.getSession(false);
               assertNull(s);
            }
        }
