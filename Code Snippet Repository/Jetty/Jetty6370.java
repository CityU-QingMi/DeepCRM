    @OnMessage
    public void onPartial(String msg, boolean fin, Session session) throws IOException
    {
        buf.append("('").append(msg).append("',").append(fin).append(')');
        if (fin)
        {
            session.getBasicRemote().sendText(buf.toString());
            buf.setLength(0);
        }
    }
