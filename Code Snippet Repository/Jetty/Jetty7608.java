        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {    
            //No session yet
            assertNull(request.getSession(false));
            
            //Create it
            HttpSession session = request.getSession();
            assertNotNull(session);
            
            //Set an attribute on it
            session.setAttribute("servlet3", "servlet3");
        }
