    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("HTTP/1.1 ").append(statusCode).append(' ').append(statusReason);
        for (Map.Entry<String, String> entry : headers.entrySet())
        {
            str.append('\n').append(entry.getKey()).append(": ").append(entry.getValue());
        }
        return str.toString();
    }
