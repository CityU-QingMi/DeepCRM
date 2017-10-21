        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException
        {
            response.setHeader("ETag",__contentETag);
            String ifnm = req.getHeader("If-None-Match");
            if (ifnm!=null && ifnm.equals(__contentETag))
                response.sendError(304);
            else
            {
                PrintWriter writer = response.getWriter();
                writer.write(__micro);
            }
        }
