    @Override
    public int doEndTag() throws JspException {
        final Log4jTaglibLogger logger = this.getLogger();

        if (this.level == null) {
            logger.catching(FQCN, Level.ERROR, this.getException());
        } else {
            logger.catching(FQCN, this.level, this.getException());
        }

        return Tag.EVAL_PAGE;
    }
