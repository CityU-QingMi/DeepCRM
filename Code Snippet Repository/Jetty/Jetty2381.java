        @Override
        public void onOpen()
        {
            super.onOpen();
            final int remaining = buffer.remaining();
            write(getConnection().getEndPoint(), buffer, new Callback()
            {
                @Override
                public void succeeded()
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("{} wrote initial {} bytes to server", DownstreamConnection.this, remaining);
                    fillInterested();
                }

                @Override
                public void failed(Throwable x)
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug(this + " failed to write initial " + remaining + " bytes to server", x);
                    close();
                    getConnection().close();
                }
            });
        }
