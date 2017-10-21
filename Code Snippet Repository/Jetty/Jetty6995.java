        @Override
        public void onWebSocketText(String message)
        {
            LOG.debug("onWebSocketText({})",message);
            calls.incrementAndGet();
            if (message.equalsIgnoreCase("openSessions"))
            {
                Collection<WebSocketSession> sessions = container.getOpenSessions();

                StringBuilder ret = new StringBuilder();
                ret.append("openSessions.size=").append(sessions.size()).append('\n');
                int idx = 0;
                for (WebSocketSession sess : sessions)
                {
                    ret.append('[').append(idx++).append("] ").append(sess.toString()).append('\n');
                }
                session.getRemote().sendStringByFuture(ret.toString());
                session.close(StatusCode.NORMAL,"ContainerSocket");
            } else if(message.equalsIgnoreCase("calls"))
            {
                session.getRemote().sendStringByFuture(String.format("calls=%,d",calls.get()));
            }
        }
