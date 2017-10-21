    public List<MessageHandlerMetadata> register(Class<? extends MessageHandler> handler)
    {
        List<MessageHandlerMetadata> metadatas = new ArrayList<>();

        boolean partial = false;

        if (MessageHandler.Partial.class.isAssignableFrom(handler))
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("supports Partial: {}",handler);
            }
            partial = true;
            Class<?> onMessageClass = ReflectUtils.findGenericClassFor(handler,MessageHandler.Partial.class);
            if (LOG.isDebugEnabled())
            {
                LOG.debug("Partial message class: {}",onMessageClass);
            }
            metadatas.add(new MessageHandlerMetadata(handler,onMessageClass,partial));
        }

        if (MessageHandler.Whole.class.isAssignableFrom(handler))
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("supports Whole: {}",handler.getName());
            }
            partial = false;
            Class<?> onMessageClass = ReflectUtils.findGenericClassFor(handler,MessageHandler.Whole.class);
            if (LOG.isDebugEnabled())
            {
                LOG.debug("Whole message class: {}",onMessageClass);
            }
            metadatas.add(new MessageHandlerMetadata(handler,onMessageClass,partial));
        }

        registered.put(handler,metadatas);
        return metadatas;
    }
