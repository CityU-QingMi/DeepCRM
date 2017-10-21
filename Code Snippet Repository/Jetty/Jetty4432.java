        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            FileInputStream in = null;
            try {
                File file = (File)req.getAttribute("fileup");
                in = new FileInputStream(file);
                
                PrintWriter out = resp.getWriter();
                out.printf("Filename [%s]\r\n", req.getParameter("fileup"));
                out.println(IO.toString(in));
            } finally {
                IO.close(in);
            }
        }
