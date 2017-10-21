    public String readRawAvailable(Socket sock) throws IOException
    {
        sock.setSoTimeout(timeoutMillis);
        // Collect response

        StringWriter writer = new StringWriter();
        InputStreamReader reader = new InputStreamReader(sock.getInputStream());

        try
        {
            IO.copy(reader,writer);
        }
        catch (SocketTimeoutException e)
        {
            /* ignore */
        }

        String rawResponse = writer.toString();
        DEBUG("--raw-response--\n" + rawResponse);
        return rawResponse;
    }
