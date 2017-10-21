        public void handle( String target,
                            Request baseRequest,
                            HttpServletRequest request,
                            HttpServletResponse response ) throws IOException,
                                                          ServletException
        {
            Map<String, String[]> params = request.getParameterMap();
            if (params.size() > 0)
            {
                response.setContentType("text/plain");
                response.getWriter().println(JSON.toString(params));
                baseRequest.setHandled(true);
            }
        }
