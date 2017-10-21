        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            //report the mimetype of a file
            String mimetype = req.getServletContext().getMimeType("file.gz");
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.write("<html><body><p>MIMETYPE="+mimetype+"</p></body</html>");
            writer.flush();
        }
