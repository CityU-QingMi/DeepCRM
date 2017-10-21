    public void send(Socket sock, CharSequence rawData) throws IOException
    {
        sock.setSoTimeout(timeoutMillis);

        DEBUG("--raw-request--\n" + rawData.toString());
        InputStream in = new ByteArrayInputStream(rawData.toString().getBytes());

        // Send request
        IO.copy(in,sock.getOutputStream());
    }
