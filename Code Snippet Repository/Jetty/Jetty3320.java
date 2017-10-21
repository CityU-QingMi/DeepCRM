        @Override
        public void handle(String target, Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException
        {
            baseRequest.setHandled(true);
            handled++;

            final AsyncContext async = request.startAsync();
            new Thread()
            {
                @Override
                public void run()
                {
                    try
                    {
                        response.setContentType("text/html;charset=utf-8");
                        response.setStatus(HttpServletResponse.SC_OK);
                        response.getWriter().println("<h1>Test</h1>"); 
                    }
                    catch (Exception ex)
                    {
                        System.err.println(ex);
                    }
                    finally
                    {
                        async.complete();
                    }
                }
            }.start();
        }
