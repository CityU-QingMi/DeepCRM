    public void sendAndIgnoreBrokenPipe(List<WebSocketFrame> send) throws IOException
    {
        try
        {
            send(send);
        }
        catch (SocketException ignore)
        {
            // Potential for SocketException (Broken Pipe) here.
            // But not in 100% of testing scenarios. It is a safe
            // exception to ignore in this testing scenario, as the
            // slow writing of the frames can result in the server
            // throwing a PROTOCOL ERROR termination/close when it
            // encounters the bad continuation frame above (this
            // termination is the expected behavior), and this
            // early socket close can propagate back to the client
            // before it has a chance to finish writing out the
            // remaining frame octets
            Assert.assertThat("Allowed to be a broken pipe",ignore.getMessage().toLowerCase(Locale.ENGLISH),containsString("broken pipe"));
        }
    }
