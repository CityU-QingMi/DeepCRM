    public ListAppender(final String name, final Filter filter, final Layout<? extends Serializable> layout, final boolean newline,
                        final boolean raw) {
        super(name, filter, layout);
        this.newLine = newline;
        this.raw = raw;
        if (layout != null && !(layout instanceof SerializedLayout)) {
            final byte[] bytes = layout.getHeader();
            if (bytes != null) {
                write(bytes);
            }
        }
    }
