        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);
            ServletOutputStream output = response.getOutputStream();
            try
            {
                output.write(new byte[64 * 1024 * 1024]);
            }
            catch (IOException x)
            {
                handlerLatch.countDown();
                throw x;
            }
        }
