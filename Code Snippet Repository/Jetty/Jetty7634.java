    @OnMessage
    public void onMessage(String data)
    {
        if(data.contains("disconnect"))
        {
            try
            {
                session.close();
            }
            catch (IOException ignore)
            {
                /* ignore */
            }
            return;
        }
        
        ListIterator<JavaxWebSocketChat> iter = members.listIterator();
        while(iter.hasNext())
        {
            JavaxWebSocketChat member = iter.next();
            
            // Test if member is now disconnected
            if(!member.session.isOpen())
            {
                iter.remove();
                continue;
            }
            
            // Async write the message back
            member.remote.sendText(data);
        }
    }
