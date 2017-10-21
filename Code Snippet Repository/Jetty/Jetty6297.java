        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            // Server specific technique
            javax.websocket.server.ServerContainer container =
                    (javax.websocket.server.ServerContainer)
                            req.getServletContext().getAttribute("javax.websocket.server.ServerContainer");
            try
            {
                container.setAsyncSendTimeout(5000);
                container.setDefaultMaxTextMessageBufferSize(1000);
                resp.setContentType("text/plain");
                resp.getWriter().printf("Configured %s - %s%n", container.getClass().getName(), container);
            }
            catch (Throwable t)
            {
                throw new ServletException(t);
            }
        }
