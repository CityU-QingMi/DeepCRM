        @OnWebSocketMessage
        public void onMessage(String data)
        {
            if (data.contains("disconnect"))
            {
                session.close();
                return;
            }

            ListIterator<ChatWebSocket> iter = members.listIterator();
            while (iter.hasNext())
            {
                ChatWebSocket member = iter.next();

                // Test if member is now disconnected
                if (!member.session.isOpen())
                {
                    iter.remove();
                    continue;
                }

                // Async write the message back.
                member.remote.sendString(data,null);
            }
        }
