        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            try
            {
                String content = exchange.exchange(baseRequest.getRequestURI());
                baseRequest.setHandled(true);
                handled++;
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().print(content);
            }
            catch (InterruptedException e)
            {
                throw new ServletException(e);
            }
        }
