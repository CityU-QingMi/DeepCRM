    @Override
    public int doEndTag() throws JspException {
        final Log4jTaglibLogger logger = TagUtils.resolveLogger(this.loggerContext, this.logger, this.factory);

        if (this.var != null) {
            this.pageContext.setAttribute(this.var, logger, this.scope);
        } else {
            TagUtils.setDefaultLogger(this.pageContext, logger);
        }

        return Tag.EVAL_PAGE;
    }
