        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException
        {
            // The bad use-case
            String pathInfo = req.getPathInfo();
            if(pathInfo != null && pathInfo.length() > 1 && pathInfo.startsWith("/"))
            {
                pathInfo = pathInfo.substring(1);
            }
            response.setHeader("X-example", pathInfo);

            // The correct use
            response.setContentType("text/plain");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println("Got request uri - " + req.getRequestURI());
        }
