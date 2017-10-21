    protected Templates getTemplates(final String path) throws TransformerException, IOException {
        if (path == null)
            throw new TransformerException("Stylesheet path is null");

        Templates templates = templatesCache.get(path);

        if (noCache || (templates == null)) {
            synchronized (templatesCache) {
                URL resource = ServletActionContext.getServletContext().getResource(path);

                if (resource == null) {
                    throw new TransformerException("Stylesheet " + path + " not found in resources.");
                }

                LOG.debug("Preparing XSLT stylesheet templates: {}", path);

                TransformerFactory factory = TransformerFactory.newInstance();
                factory.setURIResolver(getURIResolver());
                factory.setErrorListener(buildErrorListener());
                templates = factory.newTemplates(new StreamSource(resource.openStream()));
                templatesCache.put(path, templates);
            }
        }

        return templates;
    }
