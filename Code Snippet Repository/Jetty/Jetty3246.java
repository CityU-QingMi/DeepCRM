        @Override
        public void handle(String path, final Request request, HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws IOException, ServletException
        {
            httpResponse.setStatus(500);
            request.setHandled(true);

            final AsyncContext async = request.startAsync();
            // System.err.println("handle "+request.getContentLength());

            new Thread()
            {
                @Override
                public void run()
                {
                    long total=0;
                    try(InputStream in = request.getInputStream();)
                    {
                        // System.err.println("reading...");

                        byte[] b = new byte[4*4096];
                        int read;
                        while((read =in.read(b))>=0)
                            total += read;
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        total =-1;
                    }
                    finally
                    {
                        httpResponse.setStatus(200);
                        async.complete();
                        // System.err.println("read "+total);
                        __total.offer(total);
                    }
                }
            }.start();
        }
