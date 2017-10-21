   public Log4jLogEvent(final String loggerName, final Marker marker, final String loggerFQCN, final Level level,
                        final Message message, final List<Property> properties, final Throwable t) {
       this(loggerName, marker, loggerFQCN, level, message, t, null, createContextData(properties),
           ThreadContext.getDepth() == 0 ? null : ThreadContext.cloneStack(), // mutable copy
           0, // thread name
           null, // stack trace element
           0,
           null, // LOG4J2-628 use log4j.Clock for timestamps
           // LOG4J2-744 unless TimestampMessage already has one
           message instanceof TimestampMessage ? ((TimestampMessage) message).getTimestamp() :
               CLOCK.currentTimeMillis(), nanoClock.nanoTime());
   }
