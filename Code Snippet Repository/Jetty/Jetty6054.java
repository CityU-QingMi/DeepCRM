    @Override
    public Future<Void> sendText(String text)
    {
        assertMessageNotNull(text);
        if (LOG.isDebugEnabled())
        {
            LOG.debug("sendText({})",TextUtil.hint(text));
        }
        return jettyRemote.sendStringByFuture(text);
    }
