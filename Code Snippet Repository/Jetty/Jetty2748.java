    protected void logExtended(StringBuilder b, Request request, Response response) throws IOException
    {
        String referer = request.getHeader(HttpHeader.REFERER.toString());
        if (referer == null)
            b.append("\"-\" ");
        else
        {
            b.append('"');
            b.append(referer);
            b.append("\" ");
        }

        String agent = request.getHeader(HttpHeader.USER_AGENT.toString());
        if (agent == null)
            b.append("\"-\"");
        else
        {
            b.append('"');
            b.append(agent);
            b.append('"');
        }
    }
