        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            resp.setContentType("text/plain");
            PrintWriter out = resp.getWriter();
            out.println("RequestURI: " + req.getRequestURI());
            out.println("QueryString: " + req.getQueryString());
            out.print("FullURI: " + req.getRequestURI());
            if (req.getQueryString() != null)
            {
                out.print('?');
                out.print(req.getQueryString());
            }
            out.println();
        }
