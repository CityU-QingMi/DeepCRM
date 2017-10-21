        @Override
        public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
        {
            String echoText = req.getParameter("echo");

            if ( echoText == null )
            {
                throw new ServletException("echo is a required parameter");
            }
            else
            {
                res.getWriter().print(echoText);
            }
        }
