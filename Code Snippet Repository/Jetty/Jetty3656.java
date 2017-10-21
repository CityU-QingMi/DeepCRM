        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {
            response.setContentType("text/plain");

            EndPoint endPoint = baseRequest.getHttpChannel().getEndPoint();
            assertThat("Endpoint",endPoint,instanceOf(SocketChannelEndPoint.class));
            SocketChannelEndPoint channelEndPoint = (SocketChannelEndPoint)endPoint;
            Socket socket = channelEndPoint.getSocket();
            ServerConnector connector = (ServerConnector)baseRequest.getHttpChannel().getConnector();

            PrintWriter out = response.getWriter();
            out.printf("connector.getReuseAddress() = %b%n",connector.getReuseAddress());

            try
            {
                Field fld = connector.getClass().getDeclaredField("_reuseAddress");
                assertThat("Field[_reuseAddress]",fld,notNullValue());
                fld.setAccessible(true);
                Object val = fld.get(connector);
                out.printf("connector._reuseAddress() = %b%n",val);
            }
            catch (Throwable t)
            {
                t.printStackTrace(out);
            }

            out.printf("socket.getReuseAddress() = %b%n",socket.getReuseAddress());

            baseRequest.setHandled(true);
        }
