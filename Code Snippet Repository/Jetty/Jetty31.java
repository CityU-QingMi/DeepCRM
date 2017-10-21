        @Override
        public void handle( String target,
                            Request baseRequest,
                            HttpServletRequest request,
                            HttpServletResponse response ) throws IOException,
                                                          ServletException
        {
            request.setAttribute("welcome", "Hello");
            super.handle(target, baseRequest, request, response);
        }
