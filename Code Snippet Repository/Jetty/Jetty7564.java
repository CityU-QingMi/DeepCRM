        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException
        {
            String action = request.getParameter("action");
            if ("init".equals(action))
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("test", "test");
                sendResult(session, httpServletResponse.getWriter());

            }
            else
            {
                HttpSession session = request.getSession(false);

                // if we node hopped we should get the session and test should already be present
                sendResult(session, httpServletResponse.getWriter());

                if (session!=null)
                {
                    session.setAttribute("test", "test");
                }
            }
        }
