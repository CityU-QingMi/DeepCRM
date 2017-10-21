        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            ((Request)request).setHandled(true);
            try
            {

                MultipartConfigElement mpce = new MultipartConfigElement(tmpDir.getAbsolutePath(),-1, -1, 2);
                request.setAttribute(Request.__MULTIPART_CONFIG_ELEMENT, mpce);

                String field1 = request.getParameter("field1");
                assertNotNull(field1);

                Part foo = request.getPart("stuff");
                assertNotNull(foo);
                assertTrue(foo.getSize() > 0);
                response.setStatus(200);
            }
            catch (IllegalStateException e)
            {
                //expected exception because no multipart config is set up
                assertTrue(e.getMessage().startsWith("No multipart config"));
                response.setStatus(200);
            }
            catch (Exception e)
            {
                response.sendError(500);
            }
        }
