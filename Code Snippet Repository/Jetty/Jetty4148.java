        protected void doPost(HttpServletRequest request, HttpServletResponse response)
        {
            posted.set(true);
            byte[] buffer = new byte[1024];
            try
            {
                int len=request.getInputStream().read(buffer);
                while(len>0)
                {
                    response.getOutputStream().println("read "+len);
                    response.getOutputStream().flush();
                    len = request.getInputStream().read(buffer);
                }
            }
            catch (Exception e0)
            {
                ex0.set(e0);
                try
                {
                    // this read-call should fail immediately
                    request.getInputStream().read(buffer);
                }
                catch (Exception e1)
                {
                    ex1.set(e1);
                    LOG.warn(e1.toString());
                }
            }
            finally
            {
                complete.countDown();
            }
        }
