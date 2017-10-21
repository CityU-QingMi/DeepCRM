        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
          //the session should exist after the redirect
            HttpSession sess = request.getSession(false);
            assertNotNull(sess);
            assertNotNull(sess.getAttribute("servlet1"));
            assertEquals("servlet1", sess.getAttribute("servlet1"));
            
        }
