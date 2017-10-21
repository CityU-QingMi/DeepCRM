    public synchronized void release() {
        for (int i = current; i >= 0; i--) {
            handlers[i].release();
            if (annotationProcessor != null) {
                try {
                    AnnotationHelper.preDestroy(annotationProcessor, handlers[i]);
                } catch (Exception e) {
                    log.warn("Error processing preDestroy on tag instance of " 
                            + handlers[i].getClass().getName(), e);
                }
            }
        }
    }
