    private byte[] readResponse(Socket socket) throws IOException
    {
        socket.setSoTimeout(5000);
        InputStream input = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int read;
        while ((read = input.read()) >= 0)
        {
            baos.write(read);

            // Handle non-chunked end of response
            if (read == END_OF_CONTENT)
                break;

            // Handle chunked end of response
            String response = baos.toString("UTF-8");
            if (response.endsWith("\r\n0\r\n\r\n"))
                break;

            // Handle non-content responses
            if (response.contains("Content-Length: 0") && response.endsWith("\r\n\r\n"))
                break;
        }
        return baos.toByteArray();
    }
