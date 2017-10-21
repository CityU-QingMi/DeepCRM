        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);
            response.setStatus(200);

            OutputStream out = response.getOutputStream();
            for (int i=0;i<BUFFERS;i++)
            {
                out.write(content);
                out.flush();
            }
        }
