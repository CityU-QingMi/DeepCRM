    protected void build() throws Exception {
        Configuration configuration = container.getInstance(Configuration.class);
        ObjectFactory factory = container.getInstance(ObjectFactory.class);

        if (configuration != null && container != null) {
            List<UnknownHandlerConfig> unkownHandlerStack = configuration.getUnknownHandlerStack();
            unknownHandlers = new ArrayList<>();

            if (unkownHandlerStack != null && !unkownHandlerStack.isEmpty()) {
                //get UnknownHandlers in the specified order
                for (UnknownHandlerConfig unknownHandlerConfig : unkownHandlerStack) {
                    UnknownHandler uh = factory.buildUnknownHandler(unknownHandlerConfig.getName(), new HashMap<String, Object>());
                    unknownHandlers.add(uh);
                }
            } else {
                //add all available UnknownHandlers
                Set<String> unknownHandlerNames = container.getInstanceNames(UnknownHandler.class);
                for (String unknownHandlerName : unknownHandlerNames) {
                    UnknownHandler uh = container.getInstance(UnknownHandler.class, unknownHandlerName);
                    unknownHandlers.add(uh);
                }
            }
        }
    }
