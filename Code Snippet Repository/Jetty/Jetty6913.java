    public String readRequest() throws IOException
    {
        LOG.debug("Reading client request");
        StringBuilder request = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(getInputStream()));
        for (String line = in.readLine(); line != null; line = in.readLine())
        {
            if (line.length() == 0)
            {
                break;
            }
            request.append(line).append("\r\n");
            LOG.debug("read line: {}",line);
        }

        LOG.debug("Client Request:{}{}","\n",request);
        return request.toString();
    }
