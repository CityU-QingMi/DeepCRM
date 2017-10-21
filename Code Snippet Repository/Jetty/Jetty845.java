        @Override
        public boolean onContent(int request, FCGI.StreamType stream, ByteBuffer buffer)
        {
            switch (stream)
            {
                case STD_OUT:
                {
                    HttpChannelOverFCGI channel = channels.get(request);
                    if (channel != null)
                    {
                        CompletableCallback callback = new CompletableCallback()
                        {
                            @Override
                            public void resume()
                            {
                                if (LOG.isDebugEnabled())
                                    LOG.debug("Content consumed asynchronously, resuming processing");
                                process(HttpConnectionOverFCGI.this.buffer);
                            }

                            @Override
                            public void abort(Throwable x)
                            {
                                close(x);
                            }
                        };
                        // Do not short circuit these calls.
                        boolean proceed = channel.content(buffer, callback);
                        boolean async = callback.tryComplete();
                        return !proceed || async;
                    }
                    else
                    {
                        noChannel(request);
                    }
                    break;
                }
                case STD_ERR:
                {
                    LOG.info(BufferUtil.toUTF8String(buffer));
                    break;
                }
                default:
                {
                    throw new IllegalArgumentException();
                }
            }
            return false;
        }
