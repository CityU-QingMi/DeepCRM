        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            // Client specific technique
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            
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
