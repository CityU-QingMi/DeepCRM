        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            String headerPrefix = "";
            if (req.getDispatcherType() == DispatcherType.INCLUDE)
                headerPrefix = "org.eclipse.jetty.server.include.";

            resp.setHeader(headerPrefix + "included-page-key", "included-page-value");
            resp.getWriter().println("<h3> This is the included page");
        }
