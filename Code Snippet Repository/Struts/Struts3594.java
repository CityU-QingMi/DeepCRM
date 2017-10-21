    @Inject
    public void setContainer(Container container) {
        Set<String> names = container.getInstanceNames(ContentTypeHandler.class);
        for (String name : names) {
            ContentTypeHandler handler = container.getInstance(ContentTypeHandler.class, name);

            if (handler.getExtension() != null) {
                // Check for overriding handlers for the current extension
                String overrideName = container.getInstance(String.class, STRUTS_REST_HANDLER_OVERRIDE_PREFIX + handler.getExtension());
                if (overrideName != null) {
                    if (!handlersByExtension.containsKey(handler.getExtension())) {
                        handler = container.getInstance(ContentTypeHandler.class, overrideName);
                    } else {
                        // overriding handler has already been registered
                        continue;
                    }
                }
                this.handlersByExtension.put(handler.getExtension(), handler);
            }

            if (handler.getContentType() != null) {
                //dont store character encoding
                String typeOnly = handler.getContentType() ;
                int index = handler.getContentType().indexOf(';');
                if (index != -1)
                {
                    typeOnly = handler.getContentType().substring(0, index).trim();
                }

                this.handlersByContentType.put(typeOnly, handler);
            }
        }
    }
