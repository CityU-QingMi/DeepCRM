    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("id="+_id);
        builder.append(", contextpath="+_contextPath);
        builder.append(", vhost="+_vhost);
        builder.append(", accessed="+_accessed);
        builder.append(", lastaccessed="+_lastAccessed);
        builder.append(", created="+_created);
        builder.append(", cookieset="+_cookieSet);
        builder.append(", lastnode="+_lastNode);
        builder.append(", expiry="+_expiry);
        builder.append(", maxinactive="+_maxInactiveMs);
        return builder.toString();
    }
