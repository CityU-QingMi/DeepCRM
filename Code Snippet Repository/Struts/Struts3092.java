    public Tag get(Class handlerClass) throws JspException {
	Tag handler = null;
        synchronized( this ) {
            if (current >= 0) {
                handler = handlers[current--];
                return handler;
            }
        }

        // Out of sync block - there is no need for other threads to
        // wait for us to construct a tag for this thread.
        try {
            Tag instance = (Tag) handlerClass.newInstance();
            AnnotationHelper.postConstruct(annotationProcessor, instance);
            return instance;
        } catch (Exception e) {
            throw new JspException(e.getMessage(), e);
        }
    }
