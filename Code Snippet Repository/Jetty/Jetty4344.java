    protected String doRequests(String loopRequests, int loops, long pauseBetweenLoops, long pauseBeforeLast, String lastRequest) throws Exception
    {
        try (Socket socket = new Socket(_host,_port))
        {
            socket.setSoTimeout(30000);

            OutputStream out = socket.getOutputStream();

            for (int i = loops; i-- > 0;)
            {
                out.write(loopRequests.getBytes(StandardCharsets.UTF_8));
                out.flush();
                if (i > 0 && pauseBetweenLoops > 0)
                {
                    Thread.sleep(pauseBetweenLoops);
                }
            }
            if (pauseBeforeLast > 0)
            {
                Thread.sleep(pauseBeforeLast);
            }
            out.write(lastRequest.getBytes(StandardCharsets.UTF_8));
            out.flush();

            InputStream in = socket.getInputStream();
            if (loopRequests.contains("/unresponsive"))
            {
                // don't read in anything, forcing the request to time out
                Thread.sleep(_requestMaxTime * 2);
            }
            return IO.toString(in,StandardCharsets.UTF_8);
        }
    }
