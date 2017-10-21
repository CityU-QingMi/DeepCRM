        private boolean parse(ByteBuffer buffer)
        {
            while (buffer.hasRemaining())
            {
                int current = buffer.get() & 0xFF;
                if (state < token.length)
                {
                    if (Character.toLowerCase(current) != token[state])
                    {
                        state = 0;
                        continue;
                    }

                    ++state;
                    if (state == token.length)
                        return false;
                }
                else
                {
                    // Look for the ending quote.
                    if (current == '"')
                    {
                        buffer.position(buffer.position() - 1);
                        state = 0;
                        return true;
                    }
                }
            }
            return state == token.length;
        }
