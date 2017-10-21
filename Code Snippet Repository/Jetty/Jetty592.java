        @Override
        public void run()
        {
            try
            {
                while (!Thread.currentThread().isInterrupted())
                {
                    try (Socket clientSocket = serverSocket.accept();
                         BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                         PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), false))
                    {
                        // Ignore the request.
                        while (true)
                        {
                            String line = reader.readLine();
                            if (line == null || line.isEmpty())
                                break;
                        }

                        writer.append("HTTP/1.1 302 Found\r\n")
                                .append("Location: ").append(location).append("\r\n")
                                .append("Content-Length: 0\r\n")
                                .append("Connection: close\r\n")
                                .append("\r\n");
                        writer.flush();
                    }
                }
            }
            catch (SocketException x)
            {
                // ServerSocket has been closed.
            }
            catch (Throwable x)
            {
                x.printStackTrace();
            }
        }
