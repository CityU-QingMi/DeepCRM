        @Override
        public void handle(String s, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);

            response.setStatus(200);
            response.setContentType("text/plain; charset=utf-8");
            response.setHeader("Connection","close");
            PrintWriter writer = response.getWriter();
            writer.println("ctx='"+request.getContextPath()+"'");
        }
