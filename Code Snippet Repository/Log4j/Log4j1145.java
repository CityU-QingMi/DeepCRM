        public LogEventProxy(final LogEvent event, final boolean includeLocation) {
            this.loggerFQCN = event.getLoggerFqcn();
            this.marker = event.getMarker();
            this.level = event.getLevel();
            this.loggerName = event.getLoggerName();

            final Message temp = event.getMessage();
            message = temp instanceof ReusableMessage
                    ? memento((ReusableMessage) temp)
                    : temp;
            this.timeMillis = event.getTimeMillis();
            this.thrown = event.getThrown();
            this.thrownProxy = event.getThrownProxy();
            this.contextData = memento(event.getContextData());
            this.contextStack = event.getContextStack();
            this.source = includeLocation ? event.getSource() : null;
            this.threadId = event.getThreadId();
            this.threadName = event.getThreadName();
            this.threadPriority = event.getThreadPriority();
            this.isLocationRequired = includeLocation;
            this.isEndOfBatch = event.isEndOfBatch();
            this.nanoTime = event.getNanoTime();
        }
