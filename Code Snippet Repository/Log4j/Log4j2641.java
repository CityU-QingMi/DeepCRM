    @Override
    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        setStopping();
        super.stop(timeout, timeUnit, false);
        final Layout<? extends Serializable> layout = getLayout();
        if (layout != null) {
            final byte[] bytes = layout.getFooter();
            if (bytes != null) {
                write(bytes);
            }
        }
        setStopped();
        return true;
    }
