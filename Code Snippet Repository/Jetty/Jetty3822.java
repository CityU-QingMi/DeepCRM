        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            response.setContentType("text/plain");
            response.setBufferSize(128);
            byte[] b = new byte[BODY_SIZE];
            int len = 0;
            InputStream is = request.getInputStream();
            while ((len = is.read(b)) > -1)
            {
                bytes+=len;
            }

            OutputStream os = response.getOutputStream();
            for (int i = 0; i < BODY_SIZE; i++)
            {
                b[i] = 'x';
            }
            os.write(b);
            response.flushBuffer();
        }
