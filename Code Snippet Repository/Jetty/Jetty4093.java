        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            RequestDispatcher dispatcher = null;

            if(request.getParameter("include")!=null)
            {
                dispatcher = getServletContext().getRequestDispatcher(request.getParameter("include"));
                dispatcher.include(new ServletRequestWrapper(request), new ServletResponseWrapper(response));
            }
            else if(request.getParameter("forward")!=null)
            {
                dispatcher = getServletContext().getRequestDispatcher(request.getParameter("forward"));
                if (dispatcher!=null)
                    dispatcher.forward(new ServletRequestWrapper(request), new ServletResponseWrapper(response));
                else
                    response.sendError(404);
            }

        }
