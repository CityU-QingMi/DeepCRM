        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException
        {
            baseRequest.setHandled(true);

            response.setStatus(200);
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().println("URI="+request.getRequestURI());
            String user = request.getRemoteUser();
            response.getWriter().println("user="+user);
            if (request.getParameter("test_parameter")!=null)
                response.getWriter().println(request.getParameter("test_parameter"));
        }
