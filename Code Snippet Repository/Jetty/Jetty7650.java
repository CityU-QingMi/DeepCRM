        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            super.handle(target,baseRequest,request,response);
            if (Boolean.valueOf(request.getParameter("restart")))
            {
                final Server server=getServer();

                new Thread()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(100);
                            server.stop();
                            Thread.sleep(100);
                            server.start();
                        }
                        catch(Exception e)
                        {
                            LOG.warn(e);
                        }
                    }
                }.start();
            }
        }
