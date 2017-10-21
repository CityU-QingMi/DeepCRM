        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            try
            {
                synchronized (TestServlet.class)
                {
                    __sleepers++;
                    if (__sleepers > __maxSleepers)
                        __maxSleepers = __sleepers;
                }

                Thread.sleep(50);

                synchronized (TestServlet.class)
                {
                    __sleepers--;
                }

                response.setContentType("text/plain");
                response.getWriter().println("DONE!");
            }
            catch (InterruptedException e)
            {
                response.sendError(500);
            }
        }
