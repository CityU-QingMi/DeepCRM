    @Override
    public synchronized void append(final LogEvent event) {
        final Layout<? extends Serializable> layout = getLayout();
        if (layout == null) {
            events.add(event);
        } else if (layout instanceof SerializedLayout) {
            final Destination content = new Destination();
            content.byteBuffer.put(layout.getHeader());
            layout.encode(event, content);
            content.getByteBuffer().flip();
            final byte[] record = new byte[content.getByteBuffer().remaining()];
            content.getByteBuffer().get(record);
            data.add(record);
        } else {
            final Destination content = new Destination();
            layout.encode(event, content);
            content.getByteBuffer().flip();
            final byte[] record = new byte[content.getByteBuffer().remaining()];
            content.getByteBuffer().get(record);
            write(record);
        }
    }
