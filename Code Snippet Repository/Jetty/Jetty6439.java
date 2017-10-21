        public EndPoint getEndPoint() throws Exception
        {
            Session session = getSession();
            assertThat("Session type",session,instanceOf(WebSocketSession.class));

            WebSocketSession wssession = (WebSocketSession)session;
            Field fld = wssession.getClass().getDeclaredField("connection");
            fld.setAccessible(true);
            assertThat("Field: connection",fld,notNullValue());

            Object val = fld.get(wssession);
            assertThat("Connection type",val,instanceOf(AbstractWebSocketConnection.class));
            @SuppressWarnings("resource")
            AbstractWebSocketConnection wsconn = (AbstractWebSocketConnection)val;
            return wsconn.getEndPoint();
        }
