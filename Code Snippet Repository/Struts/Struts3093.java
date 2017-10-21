    public void reuse(Tag handler) {
        synchronized( this ) {
            if (current < (handlers.length - 1)) {
                handlers[++current] = handler;
                return;
            }
        }
        // There is no need for other threads to wait for us to release
        handler.release();
        if (annotationProcessor != null) {
            try {
                AnnotationHelper.preDestroy(annotationProcessor, handler);
            } catch (Exception e) {
                log.warn("Error processing preDestroy on tag instance of " 
                        + handler.getClass().getName(), e);
            }
        }
    }
