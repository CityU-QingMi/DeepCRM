    public void destroy() {
        if (theServlet != null) {
            theServlet.destroy();
            AnnotationProcessor annotationProcessor = (AnnotationProcessor) config.getServletContext().getAttribute(AnnotationProcessor.class.getName());
            if (annotationProcessor != null) {
                try {
                    annotationProcessor.preDestroy(theServlet);
                } catch (Exception e) {
                    // Log any exception, since it can't be passed along
                    log.error(Localizer.getMessage("jsp.error.file.not.found",
                           e.getMessage()), e);
                }
            }
        }
    }
