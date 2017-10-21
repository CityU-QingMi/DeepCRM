    @Test
    public void testEvents() throws Exception
    {
        final String name = "event1";
        final String data = "data2";
        class S extends EventSourceServlet
        {
            @Override
            protected EventSource newEventSource(HttpServletRequest request)
            {
                return new EventSource()
                {
                    public void onOpen(Emitter emitter) throws IOException
                    {
                        emitter.event(name, data);
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

        String line1 = reader.readLine();
        Assert.assertEquals("event: " + name, line1);
        String line2 = reader.readLine();
        Assert.assertEquals("data: " + data, line2);
        String line3 = reader.readLine();
        Assert.assertEquals(0, line3.length());

        socket.close();
    }
