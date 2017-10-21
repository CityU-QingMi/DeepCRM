        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            if (request.getParameter("session")!=null)
                request.getSession(true);
            if (request.getParameter("sleep")!=null)
            {
                try
                {
                    Thread.sleep(Long.parseLong(request.getParameter("sleep")));
                }
                catch(InterruptedException e)
                {
                }
            }

            if (request.getParameter("lines")!=null)
            {
                int count = Integer.parseInt(request.getParameter("lines"));
                for(int i = 0; i < count; ++i)
                {
                    response.getWriter().append("Line: " + i + "\n");
                    response.flushBuffer();

                    try
                    {
                        Thread.sleep(10);
                    }
                    catch(InterruptedException e)
                    {
                    }
                }
            }

            response.setContentType("text/plain");
        }
