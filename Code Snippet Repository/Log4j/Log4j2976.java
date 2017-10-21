    private void setUp(final Level level) {
        this.tag = new LoggingMessageTagSupport() {
            private static final long serialVersionUID = 1L;

            @Override
            protected Level getLevel() {
                return level;
            }
        };
        this.tag.setPageContext(new MockPageContext());
        this.tag.setLogger(this.logger);
    }
