        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);
            String code=request.getParameter("code");
            if (code!=null)
                response.setStatus(Integer.parseInt(code));

            response.setHeader("Custom","Value");
            response.setContentType("text/plain");
            String content = "Hello from Jetty using "+request.getProtocol() +"\n";
            content+="uri="+request.getRequestURI()+"\n";
            content+="date="+new Date()+"\n";
            response.setContentLength(content.length());
            response.getOutputStream().print(content);
        }
