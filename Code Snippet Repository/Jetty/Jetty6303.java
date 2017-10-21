    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("EchoCase['");
        str.append(path);
        str.append("',").append(serverPojo.getName());
        str.append(",messages[").append(messages.size());
        str.append("]=");
        boolean delim = false;
        for (Object msg : messages)
        {
            if (delim)
            {
                str.append(",");
            }
            if (msg instanceof String)
            {
                str.append("'").append(msg).append("'");
            }
            else
            {
                str.append("(").append(msg.getClass().getName()).append(")");
                str.append(msg);
            }
            delim = true;
        }
        str.append("]");
        return str.toString();
    }
