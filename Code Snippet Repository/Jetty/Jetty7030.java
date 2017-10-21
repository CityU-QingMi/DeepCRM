    @Test
    public void testWebSocketProtocolResponse() throws Exception
    {
        try (Socket client = new Socket("localhost", server.getPort()))
        {
            byte[] key = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
            StringBuilder request = new StringBuilder();
            request.append("GET / HTTP/1.1\r\n")
                    .append("Host: localhost\r\n")
                    .append("Connection: Upgrade\r\n")
                    .append("Upgrade: websocket\r\n")
                    .append("Sec-WebSocket-version: 13\r\n")
                    .append("Sec-WebSocket-Key:").append(B64Code.encode(key)).append("\r\n")
                    .append("Sec-WebSocket-Protocol: echo\r\n")
                    .append("\r\n");

            OutputStream output = client.getOutputStream();
            output.write(request.toString().getBytes(StandardCharsets.UTF_8));
            output.flush();

            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line = input.readLine();
            Assert.assertTrue(line.contains(" 101 "));
            HttpFields fields = new HttpFields();
            while ((line = input.readLine()) != null)
            {
                if (line.isEmpty())
                    break;
                int colon = line.indexOf(':');
                Assert.assertTrue(colon > 0);
                String name = line.substring(0, colon).trim();
                String value = line.substring(colon + 1).trim();
                fields.add(name, value);
            }

            Assert.assertEquals(1, fields.getValuesList("Sec-WebSocket-Protocol").size());
        }
    }
