    @Test
    public void testMultiLineData() throws Exception
    {
        String data1 = "data1";
        String data2 = "data2";
        String data3 = "data3";
        String data4 = "data4";
        final String data = data1 + "\r\n" + data2 + "\r" + data3 + "\n" + data4;
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

        String line1 = reader.readLine();
        Assert.assertEquals("data: " + data1, line1);
        String line2 = reader.readLine();
        Assert.assertEquals("data: " + data2, line2);
        String line3 = reader.readLine();
        Assert.assertEquals("data: " + data3, line3);
        String line4 = reader.readLine();
        Assert.assertEquals("data: " + data4, line4);
        String line5 = reader.readLine();
        Assert.assertEquals(0, line5.length());

        socket.close();
    }
