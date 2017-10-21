        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("utf-8");
            String pathInfo = req.getPathInfo();

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            try (InputStream in = req.getServletContext().getResourceAsStream(pathInfo))
            {
                if (in == null)
                {
                    out.write("<null>".getBytes(StandardCharsets.UTF_8));
                }
                else
                {
                    IO.copy(in, out);
                }
            }

            String resourceContents = new String(out.toByteArray(), StandardCharsets.UTF_8);
            resp.getWriter().printf("Resource '%s': %s", pathInfo, resourceContents);
        }
