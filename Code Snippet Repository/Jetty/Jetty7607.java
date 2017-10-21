        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            //forward to yet another servlet to do the creation
            assertNull(request.getSession(false));

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/three");
            dispatcher.forward(request, response);
            
            //the session should exist after the forward
            HttpSession sess = request.getSession(false);
            assertNotNull(sess);
            assertNotNull(sess.getAttribute("servlet3"));
        }
