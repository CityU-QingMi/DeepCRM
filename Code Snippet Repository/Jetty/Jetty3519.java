        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);
            response.setStatus(200);

            try
            {
                InputStream in = request.getInputStream();
                String input= IO.toString(in);
                response.getWriter().printf("read %d%n",input.length());
            }
            catch(Exception e)
            {
                response.getWriter().printf("caught %s%n",e); 
            }
        }
