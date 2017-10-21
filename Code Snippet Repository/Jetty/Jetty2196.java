        @Override
        public void handle(String target, Request baseRequest,
                        HttpServletRequest request, HttpServletResponse response)
                        throws IOException {
                if (HttpServiceErrorHandlerHelper.getCustomErrorHandler() != null)
                {
                        try
                        {
                                HttpServiceErrorHandlerHelper.getCustomErrorHandler().service(request, response);
                        }
                        catch (ServletException e)
                        {
                                //well
                        }
                }
                if (!response.isCommitted())
                {
                        super.handle(target, baseRequest, request, response);
                }
        }
