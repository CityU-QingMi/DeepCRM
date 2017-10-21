    @Override
    public final int doEndTag() throws JspException {
        final Log4jTaglibLogger logger = this.getLogger();
        final Level level = this.getLevel();
        final Marker marker = this.getMarker();

        if (TagUtils.isEnabled(logger, level, marker)) {
            final Object message = this.getMessage();
            final Throwable exception = this.getException();
            if (message instanceof Message) {
                logger.logIfEnabled(FQCN, level, marker, (Message) message, exception);
            } else if (message instanceof String) {
                Message data;
                if (this.attributes.size() > 0) {
                    data = logger.getMessageFactory().newMessage((String) message, this.attributes.toArray());
                } else {
                    data = logger.getMessageFactory().newMessage((String) message);
                }
                logger.logIfEnabled(FQCN, level, marker, data, exception);
            } else {
                logger.logIfEnabled(FQCN, level, marker, logger.getMessageFactory().newMessage(message), exception);
            }
        }

        return Tag.EVAL_PAGE;
    }
