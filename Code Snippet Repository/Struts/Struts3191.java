    protected void insertTagHandlerFactory(String tagName, int sequence, TagHandlerFactory factory) {

        if (tagName != null && factory != null && this.handlerFactories != null) {

            List<TagHandlerFactory> tagHandlerFactories = handlerFactories.get(tagName);

            if (tagHandlerFactories == null) {
                tagHandlerFactories = new ArrayList<TagHandlerFactory>(); //TODO: Could use public FactoryList here
            }

            if (sequence > tagHandlerFactories.size()) {
                sequence = tagHandlerFactories.size();
            }

            //TODO, need to account for TagHandlers vs. TagSerializers here
            tagHandlerFactories.add(sequence, factory);
        }
    }
