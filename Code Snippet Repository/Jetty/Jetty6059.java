    @Override
    public void sendText(String text) throws IOException
    {
        assertMessageNotNull(text);
        if (LOG.isDebugEnabled())
        {
            LOG.debug("sendText({})",TextUtil.hint(text));
        }
        jettyRemote.sendString(text);
    }
