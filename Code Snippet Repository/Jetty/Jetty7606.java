        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            //Don't create a session, just forward to another session in the same context
            assertNull(request.getSession(false));
            
            //The session will be created by the other servlet, so will exist as this dispatch returns
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/two");
            dispatcher.forward(request, response);
   
            HttpSession sess = request.getSession(false);
            assertNotNull(sess);
            assertNotNull(sess.getAttribute("servlet3"));
            sess.setAttribute("servlet1", "servlet1");
            
            if (_synchronizer != null)
                _synchronizer.countDown();
        }
