    @Override
    public void addMessageHandler(MessageHandler handler) throws IllegalStateException
    {
        Objects.requireNonNull(handler, "MessageHandler cannot be null");

        synchronized (wrappers)
        {
            for (MessageHandlerMetadata metadata : messageHandlerFactory.getMetadata(handler.getClass()))
            {
                DecoderFactory.Wrapper wrapper = decoderFactory.getWrapperFor(metadata.getMessageClass());
                if (wrapper == null)
                {
                    StringBuilder err = new StringBuilder();
                    err.append("Unable to find decoder for type <");
                    err.append(metadata.getMessageClass().getName());
                    err.append("> used in <");
                    err.append(metadata.getHandlerClass().getName());
                    err.append(">");
                    throw new IllegalStateException(err.toString());
                }

                MessageType key = wrapper.getMetadata().getMessageType();
                MessageHandlerWrapper other = wrappers[key.ordinal()];
                if (other != null)
                {
                    StringBuilder err = new StringBuilder();
                    err.append("Encountered duplicate MessageHandler handling message type <");
                    err.append(wrapper.getMetadata().getObjectType().getName());
                    err.append(">, ").append(metadata.getHandlerClass().getName());
                    err.append("<");
                    err.append(metadata.getMessageClass().getName());
                    err.append("> and ");
                    err.append(other.getMetadata().getHandlerClass().getName());
                    err.append("<");
                    err.append(other.getMetadata().getMessageClass().getName());
                    err.append("> both implement this message type");
                    throw new IllegalStateException(err.toString());
                }
                else
                {
                    MessageHandlerWrapper handlerWrapper = new MessageHandlerWrapper(handler,metadata,wrapper);
                    wrappers[key.ordinal()] = handlerWrapper;
                }
            }

            // Update handlerSet
            updateMessageHandlerSet();
        }
    }
