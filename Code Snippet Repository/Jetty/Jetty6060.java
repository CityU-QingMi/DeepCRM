    @Override
    public void sendText(String partialMessage, boolean isLast) throws IOException
    {
        assertMessageNotNull(partialMessage);
        if (LOG.isDebugEnabled())
        {
            LOG.debug("sendText({},{})",TextUtil.hint(partialMessage),isLast);
        }
        jettyRemote.sendPartialString(partialMessage,isLast);
    }
