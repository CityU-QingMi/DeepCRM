        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);
            
            if(request.getDispatcherType() == DispatcherType.REQUEST)
            {
                response.setStatus(200);
    
                int l = request.getContentLength();
                int r = 0;
                while (r < l)
                {
                    if (request.getInputStream().read() >= 0)
                        r++;
                }
    
                response.getOutputStream().write(("Read Input " + r + "\r\n").getBytes());
            }
            else
            {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR_500);
            }
        }
