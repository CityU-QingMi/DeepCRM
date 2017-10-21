    private void initTranslator(final RingBufferLogEventTranslator translator, final String fqcn,
            final Level level, final Marker marker, final Message message, final Throwable thrown) {

        translator.setBasicValues(this, name, marker, fqcn, level, message, //
                // don't construct ThrowableProxy until required
                thrown,

                // needs shallow copy to be fast (LOG4J2-154)
                ThreadContext.getImmutableStack(), //

                // location (expensive to calculate)
                calcLocationIfRequested(fqcn), //
                CLOCK.currentTimeMillis(), //
                nanoClock.nanoTime() //
        );
    }
