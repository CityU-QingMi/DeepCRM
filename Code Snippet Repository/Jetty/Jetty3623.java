        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            ((Request)request).setHandled(true);
            try
            {

                MultipartConfigElement mpce = new MultipartConfigElement(tmpDir.getAbsolutePath(),-1, -1, 2);
                request.setAttribute(Request.__MULTIPART_CONFIG_ELEMENT, mpce);

                //We should get an error when we getParams if there was a problem parsing the multipart
                request.getPart("xxx");
                //A 200 response is actually wrong here
            }
            catch (RuntimeException e)
            {
                response.sendError(500);
            }
        }
