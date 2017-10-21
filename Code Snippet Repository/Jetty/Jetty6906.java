    @Override
    public void expectServerDisconnect()
    {
        if (eof)
        {
            return;
        }

        try
        {
            int len = in.read();
            if (len == (-1))
            {
                // we are disconnected
                eof = true;
                return;
            }

            Assert.assertThat("Expecting no data and proper socket disconnect (issued from server)",len,is(-1));
        }
        catch (SocketTimeoutException e)
        {
            LOG.warn(e);
            Assert.fail("Expected a server initiated disconnect, instead the read timed out");
        }
        catch (IOException e)
        {
            // acceptable path
        }
    }
