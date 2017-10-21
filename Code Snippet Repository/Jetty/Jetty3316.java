        @Override
        public void handle(String target, Request baseRequest,
                HttpServletRequest request, HttpServletResponse response)
                        throws IOException, ServletException 
        {
            handling.set(true);
            latch.countDown();
            int c=0;
            try
            {
                int content_length = request.getContentLength();
                InputStream in = request.getInputStream();

                while(true)
                {
                    if (in.read()<0)
                        break;
                    c++;
                }

                baseRequest.setHandled(true);
                response.setStatus(200);
                response.getWriter().printf("read %d/%d%n",c,content_length);
            }
            catch(Throwable th)
            {
                thrown.set(th);
            }
            finally
            {
                handling.set(false);
            }
        }
