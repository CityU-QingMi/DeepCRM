    @Override
    protected synchronized void write(final byte[] bytes, final int offset, final int length,
            final boolean immediateFlush) {
        if (isLocking) {
            try {
                @SuppressWarnings("resource")
                final FileChannel channel = ((FileOutputStream) getOutputStream()).getChannel();
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
                try (final FileLock lock = channel.lock(0, Long.MAX_VALUE, false)) {
                    super.write(bytes, offset, length, immediateFlush);
                }
            } catch (final IOException ex) {
                throw new AppenderLoggingException("Unable to obtain lock on " + getName(), ex);
            }
        } else {
            super.write(bytes, offset, length, immediateFlush);
        }
    }
