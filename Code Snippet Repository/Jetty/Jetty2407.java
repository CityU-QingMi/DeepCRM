        @Override
        public void transform(ByteBuffer input, boolean finished, List<ByteBuffer> output) throws IOException
        {
            if (input.hasRemaining() && head != null)
            {
                int lnPos = findLineFeed(input);
                if (lnPos == -1)
                {
                    // no linefeed found, copy it all
                    copyHeadBytes(input,input.limit());
                }
                else
                {
                    // found linefeed
                    copyHeadBytes(input,lnPos);
                    output.addAll(getHeadBytes());
                    // mark head as sent
                    head = null;
                }
            }

            if (finished && head != null)
            {
                output.addAll(getHeadBytes());
            }
        }
