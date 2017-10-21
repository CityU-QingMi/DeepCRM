    @Override
    public int doEndTag() throws JspException {
        final Log4jTaglibLogger logger = this.getLogger();

        if (TagUtils.isEnabled(logger, Level.TRACE, null)) {
            if (this.result == null) {
                logger.exit(FQCN, null);
            } else {
                logger.exit(FQCN, this.result);
            }
        }

        return Tag.EVAL_PAGE;
    }
