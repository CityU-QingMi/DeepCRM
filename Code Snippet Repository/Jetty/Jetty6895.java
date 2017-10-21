    @Override
    public void onConnectionStateChange(ConnectionState state)
    {
        LOG.debug("CLIENT onConnectionStateChange() - {}",state);
        switch (state)
        {
            case CLOSED:
                // Per Spec, client should not initiate disconnect on its own
                // this.disconnect();
                break;
            case CLOSING:
                CloseInfo close = ioState.getCloseInfo();

                WebSocketFrame frame = close.asFrame();
                LOG.debug("Issuing: {}",frame);
                try
                {
                    write(frame);
                }
                catch (IOException e)
                {
                    LOG.debug(e);
                }
                break;
            default:
                /* do nothing */
                break;
        }
    }
