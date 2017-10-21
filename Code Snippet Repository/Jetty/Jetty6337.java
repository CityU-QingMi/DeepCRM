    @OnMessage
    public String onMessage(Session session, String message)
    {
        if ("pathParams".equalsIgnoreCase(message))
        {
            StringBuilder ret = new StringBuilder();
            ret.append("pathParams");
            Map<String, String> pathParams = session.getPathParameters();
            if (pathParams == null)
            {
                ret.append("=<null>");
            }
            else
            {
                ret.append('[').append(pathParams.size()).append(']');
                List<String> keys = new ArrayList<>();
                for (String key : pathParams.keySet())
                {
                    keys.add(key);
                }
                Collections.sort(keys);
                for (String key : keys)
                {
                    String value = pathParams.get(key);
                    ret.append(": '").append(key).append("'=").append(value);
                }
            }
            return ret.toString();
        }

        if ("requestUri".equalsIgnoreCase(message))
        {
            StringBuilder ret = new StringBuilder();
            ret.append("requestUri=");
            URI uri = session.getRequestURI();
            if (uri == null)
            {
                ret.append("=<null>");
            }
            else
            {
                ret.append(uri.toASCIIString());
            }
            return ret.toString();
        }

        // simple echo
        return "echo:'" + message + "'";
    }
