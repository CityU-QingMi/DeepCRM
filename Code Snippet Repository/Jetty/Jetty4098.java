        @Override
        public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
        {
            ServletContext targetContext = getServletConfig().getServletContext().getContext("/resource");

            RequestDispatcher dispatcher = targetContext.getRequestDispatcher(req.getPathInfo());

            if ( "true".equals(req.getParameter("wrapped")))
            {
                if (req.getParameter("do").equals("forward"))
                {
                    dispatcher.forward(new HttpServletRequestWrapper(req),new HttpServletResponseWrapper(res));
                }
                else if (req.getParameter("do").equals("include"))
                {
                    dispatcher.include(new HttpServletRequestWrapper(req),new HttpServletResponseWrapper(res));
                }
                else
                {
                    throw new ServletException("type of forward or include is required");
                }
            }
            else
            {
                if (req.getParameter("do").equals("forward"))
                {
                    dispatcher.forward(req,res);
                }
                else if (req.getParameter("do").equals("include"))
                {
                    dispatcher.include(req,res);
                }
                else
                {
                    throw new ServletException("type of forward or include is required");
                }
            }
        }
