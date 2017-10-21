    private void setUp(final Object page) {
        this.context = new MockPageContext() {
            @Override
            public Object getPage() {
                return page == null ? this : page;
            }
        };
        this.tag = new LoggerAwareTagSupport() {
            private static final long serialVersionUID = 1L;
        };
        this.tag.setPageContext(this.context);
    }
