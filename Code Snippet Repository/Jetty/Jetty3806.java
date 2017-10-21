        @Override
        public void handle(String path, Request request, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, ServletException
        {
            final CountDownLatch latch = _latch;
            try
            {
                super.handle(path, request, httpRequest, httpResponse);
            }
            finally
            {
                latch.countDown();
            }
        }
