    protected final Log4jTaglibLogger getLogger() throws JspException {
        if (this.logger != null) {
            return TagUtils.resolveLogger(this.loggerContext, this.logger, null);
        }
        Log4jTaglibLogger logger = TagUtils.getDefaultLogger(this.pageContext);
        if (logger == null) {
            final String name = this.pageContext.getPage().getClass().getName();
            logger = TagUtils.resolveLogger(this.loggerContext, name, null);
            TagUtils.setDefaultLogger(this.pageContext, logger);
        }
        return logger;
    }
