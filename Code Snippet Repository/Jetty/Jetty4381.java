    @Test
    public void testEncoding() throws Exception
    {
        // The EURO symbol
        final String data = "\u20AC";
        class S extends EventSourceServlet
        {
            @Override
            protected EventSource newEventSource(HttpServletRequest request)
            {
                return new EventSource()
                {
                    public void onOpen(Emitter emitter) throws IOException
                    {
                        emitter.data(data);
                    }

                    public void onClose()
                    {
                    }
                };
            }
        }

        String servletPath = "/eventsource";
        context.addServlet(new ServletHolder(new S()), servletPath);

        Socket socket = new Socket("localhost", connector.getLocalPort());
        writeHTTPRequest(socket, servletPath);
        BufferedReader reader = readAndDiscardHTTPResponse(socket);

        String line = reader.readLine();
        String received = "";
        while (line != null)
        {
            received += line;
            if (line.length() == 0)
                break;
            line = reader.readLine();
        }

        Assert.assertEquals("data: " + data, received);

        socket.close();
    }
