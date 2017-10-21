            private boolean parse(ByteBuffer buffer) throws IOException
            {
                while (buffer.hasRemaining())
                {
                    byte current = buffer.get();
                    if (cursor == 1)
                        response = current & 0xFF;
                    ++cursor;
                    if (cursor == EXPECTED_LENGTH)
                    {
                        onSocks4Response(response);
                        return true;
                    }
                }
                return false;
            }
