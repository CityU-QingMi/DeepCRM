    @Override
    protected synchronized void writeToDestination(final byte[] bytes, final int offset, final int length) {
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
                    super.writeToDestination(bytes, offset, length);
                }
            } catch (final IOException ex) {
                throw new AppenderLoggingException("Unable to obtain lock on " + getName(), ex);
            }
        } else {
            super.writeToDestination(bytes, offset, length);
        }
    }
