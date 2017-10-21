    public void renderTag(String tagName, TemplateRenderingContext context) {
        if (tagName.endsWith(".java")) {
            tagName = tagName.substring(0, tagName.length() - ".java".length());
        }

        List<TagHandler> handlers = new ArrayList<TagHandler>();
        List<TagHandlerFactory> factories = handlerFactories.get(tagName);
        if (factories == null) {
            throw new StrutsException("Unable to find handlers for tag " + tagName);
        }

        TagHandler prev = null;
        for (int x = factories.size() - 1; x >= 0; x--) {
            prev = factories.get(x).create(prev);
            prev.setup(context);
            handlers.add(0, prev);
        }

        // TagSerializer ser = (TagSerializer) handlers.get(handlers.size() - 1);

        TagGenerator gen = (TagGenerator) handlers.get(0);
        try {
            LOG.trace("Rendering tag [{}]", tagName);
            gen.generate();
        } catch (IOException ex) {
            throw new StrutsException("Unable to write tag: " + tagName, ex);
        }
    }
