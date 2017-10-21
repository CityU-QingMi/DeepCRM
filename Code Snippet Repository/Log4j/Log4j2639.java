    @Override
    public synchronized void append(final LogEvent event) {
        final Layout<? extends Serializable> layout = getLayout();
        if (layout == null) {
            if (event instanceof MutableLogEvent) {
                // must take snapshot or subsequent calls to logger.log() will modify this event
                events.add(((MutableLogEvent) event).createMemento());
            } else {
                events.add(event);
            }
        } else if (layout instanceof SerializedLayout) {
            final byte[] header = layout.getHeader();
            final byte[] content = layout.toByteArray(event);
            final byte[] record = new byte[header.length + content.length];
            System.arraycopy(header, 0, record, 0, header.length);
            System.arraycopy(content, 0, record, header.length, content.length);
            data.add(record);
        } else {
            write(layout.toByteArray(event));
        }
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }
