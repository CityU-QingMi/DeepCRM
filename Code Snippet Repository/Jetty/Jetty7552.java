        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException
        {
            String action = request.getParameter("action");
            if ("init".equals(action))
            {
                Session session = (Session)request.getSession(true);
                session.setAttribute("a.b.c",System.currentTimeMillis());               
                sendResult(session,httpServletResponse.getWriter());

            }
            else
            {
                Session session = (Session)request.getSession(false);
                assertNotNull(session);     
                assertNotNull(session.getAttribute("a.b.c"));
                sendResult(session,httpServletResponse.getWriter());
            }

        }
