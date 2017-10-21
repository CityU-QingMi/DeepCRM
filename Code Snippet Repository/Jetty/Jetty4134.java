        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException
        {
            if (req.getParameter("vary")!=null)
                response.addHeader("Vary",req.getParameter("vary"));
            response.setHeader("ETag",__contentETag);
            String ifnm = req.getHeader("If-None-Match");    
            if (ifnm!=null && ifnm.equals(__contentETag))
                response.sendError(304);
            else
            {
                PrintWriter writer = response.getWriter();
                writer.write(__content);
            }
        }
