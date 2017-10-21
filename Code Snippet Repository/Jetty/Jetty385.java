        private void writeSocks4Connect()
        {
            HttpDestination destination = (HttpDestination)context.get(HttpClientTransport.HTTP_DESTINATION_CONTEXT_KEY);
            String host = destination.getHost();
            short port = (short)destination.getPort();
            Matcher matcher = IPv4_PATTERN.matcher(host);
            if (matcher.matches())
            {
                // SOCKS 4
                ByteBuffer buffer = ByteBuffer.allocate(9);
                buffer.put((byte)4).put((byte)1).putShort(port);
                for (int i = 1; i <= 4; ++i)
                    buffer.put((byte)Integer.parseInt(matcher.group(i)));
                buffer.put((byte)0);
                buffer.flip();
                getEndPoint().write(this, buffer);
            }
            else
            {
                // SOCKS 4A
                byte[] hostBytes = host.getBytes(StandardCharsets.UTF_8);
                ByteBuffer buffer = ByteBuffer.allocate(9 + hostBytes.length + 1);
                buffer.put((byte)4).put((byte)1).putShort(port);
                buffer.put((byte)0).put((byte)0).put((byte)0).put((byte)1).put((byte)0);
                buffer.put(hostBytes).put((byte)0);
                buffer.flip();
                getEndPoint().write(this, buffer);
            }
        }
