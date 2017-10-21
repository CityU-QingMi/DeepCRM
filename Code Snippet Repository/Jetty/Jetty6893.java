        @Override
        public void run()
        {
            LOG.debug("Reading frames from server");

            byte buf[] = new byte[BUFFER_SIZE];
            try
            {
                if ((remainingBuffer != null) && (remainingBuffer.remaining() > 0))
                {
                    LOG.debug("Reading bytes received during response header parse: {}",BufferUtil.toDetailString(remainingBuffer));
                    totalBytes += remainingBuffer.remaining();
                    totalReadOps++;
                    parser.parse(remainingBuffer);
                }

                int len = 0;
                int available = 0;
                while (!eof)
                {
                    available = in.available();
                    len = in.read(buf,0,Math.min(available,buf.length));
                    totalReadOps++;
                    if (len < 0)
                    {
                        eof = true;
                        break;
                    }
                    else if (len > 0)
                    {
                        totalBytes += len;
                        ByteBuffer bbuf = ByteBuffer.wrap(buf,0,len);
                        if (LOG.isDebugEnabled())
                        {
                            LOG.debug("Read {} bytes: {}",len,BufferUtil.toDetailString(bbuf));
                        }
                        totalParseOps++;
                        parser.parse(bbuf);
                    }
                }
            }
            catch (IOException e)
            {
                LOG.debug(e);
            }
        }
