    private static void unsafeUnmap(final MappedByteBuffer mbb) throws PrivilegedActionException {
        LOGGER.debug("MMapAppender unmapping old buffer...");
        final long startNanos = System.nanoTime();
        AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
            @Override
            public Object run() throws Exception {
                final Method getCleanerMethod = mbb.getClass().getMethod("cleaner");
                getCleanerMethod.setAccessible(true);
                final Object cleaner = getCleanerMethod.invoke(mbb); // sun.misc.Cleaner instance
                final Method cleanMethod = cleaner.getClass().getMethod("clean");
                cleanMethod.invoke(cleaner);
                return null;
            }
        });
        final float millis = (float) ((System.nanoTime() - startNanos) / NANOS_PER_MILLISEC);
        LOGGER.debug("MMapAppender unmapped buffer OK in {} millis", millis);
    }
