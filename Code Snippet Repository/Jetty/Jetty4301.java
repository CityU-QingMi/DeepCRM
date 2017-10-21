        @Override
        public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
        {
            String uri = req.getRequestURI();
            if (uri.endsWith(".deferred"))
            {
                // System.err.println("type for "+uri.substring(0,uri.length()-9)+" is "+getServletContext().getMimeType(uri.substring(0,uri.length()-9)));
                resp.setContentType(getServletContext().getMimeType(uri.substring(0,uri.length() - 9)));
            }

            doGet(req,resp);
        }
