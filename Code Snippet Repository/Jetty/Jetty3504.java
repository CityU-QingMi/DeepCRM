        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            _endp = baseRequest.getHttpChannel().getEndPoint();
            response.setHeader("test", "value");
            response.setStatus(200);
            response.setContentType("text/plain");
            response.getWriter().println("Now is the time for all good men to come to the aid of the party");
            response.getWriter().flush();
            response.flushBuffer();

            throw new ServletException(new Exception("exception after commit"));
        }
