    private void handleRingBufferFull(final RingBufferLogEventTranslator translator) {
        final EventRoute eventRoute = loggerDisruptor.getEventRoute(translator.level);
        switch (eventRoute) {
            case ENQUEUE:
                loggerDisruptor.enqueueLogMessageInfo(translator);
                break;
            case SYNCHRONOUS:
                logMessageInCurrentThread(translator.fqcn, translator.level, translator.marker, translator.message,
                        translator.thrown);
                break;
            case DISCARD:
                break;
            default:
                throw new IllegalStateException("Unknown EventRoute " + eventRoute);
        }
    }
