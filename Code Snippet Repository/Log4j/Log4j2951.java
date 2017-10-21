    @Override
    public int doEndTag() throws JspException {
        final Log4jTaglibLogger logger = this.getLogger();

        if (TagUtils.isEnabled(logger, Level.TRACE, null)) {
            if (this.attributes.size() == 0) {
                logger.entry(FQCN);
            } else {
                logger.entry(FQCN, this.attributes.toArray());
            }
        }

        return Tag.EVAL_PAGE;
    }
