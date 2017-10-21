    private void sendSingleMessage(HttpServletResponse response, Member member) throws IOException
    {
        response.setContentType("text/json;charset=utf-8");
        StringBuilder buf = new StringBuilder();

        buf.append("{\"from\":\"");
        buf.append(member._queue.poll());
        buf.append("\",");

        String returnMessage = member._queue.poll();
        int quote = returnMessage.indexOf('"');
        while (quote >= 0)
        {
            returnMessage = returnMessage.substring(0, quote) + '\\' + returnMessage.substring(quote);
            quote = returnMessage.indexOf('"', quote + 2);
        }
        buf.append("\"chat\":\"");
        buf.append(returnMessage);
        buf.append("\"}");
        byte[] bytes = buf.toString().getBytes("utf-8");
        response.setContentLength(bytes.length);
        response.getOutputStream().write(bytes);
    }
