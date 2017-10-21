        @Override
        public void onFillable()
        {
            try
            {
                while (true)
                {
                    // Avoid to read too much from the socket: ask
                    // the parser how much left there is to read.
                    ByteBuffer buffer = BufferUtil.allocate(parser.expected());
                    int filled = getEndPoint().fill(buffer);
                    if (LOG.isDebugEnabled())
                        LOG.debug("Read SOCKS4 connect response, {} bytes", filled);

                    if (filled < 0)
                        throw new IOException("SOCKS4 tunnel failed, connection closed");

                    if (filled == 0)
                    {
                        fillInterested();
                        return;
                    }

                    if (parser.parse(buffer))
                        return;
                }
            }
            catch (Throwable x)
            {
                failed(x);
            }
        }
