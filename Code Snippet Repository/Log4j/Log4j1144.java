        public Builder(final LogEvent other) {
            Objects.requireNonNull(other);
            if (other instanceof RingBufferLogEvent) {
                ((RingBufferLogEvent) other).initializeBuilder(this);
                return;
            }
            if (other instanceof MutableLogEvent) {
                ((MutableLogEvent) other).initializeBuilder(this);
                return;
            }
            this.loggerFqcn = other.getLoggerFqcn();
            this.marker = other.getMarker();
            this.level = other.getLevel();
            this.loggerName = other.getLoggerName();
            this.message = other.getMessage();
            this.timeMillis = other.getTimeMillis();
            this.thrown = other.getThrown();
            this.contextStack = other.getContextStack();
            this.includeLocation = other.isIncludeLocation();
            this.endOfBatch = other.isEndOfBatch();
            this.nanoTime = other.getNanoTime();

            // Avoid unnecessarily initializing thrownProxy, threadName and source if possible
            if (other instanceof Log4jLogEvent) {
                final Log4jLogEvent evt = (Log4jLogEvent) other;
                this.contextData = evt.contextData;
                this.thrownProxy = evt.thrownProxy;
                this.source = evt.source;
                this.threadId = evt.threadId;
                this.threadName = evt.threadName;
                this.threadPriority = evt.threadPriority;
            } else {
                if (other.getContextData() instanceof StringMap) {
                    this.contextData = (StringMap) other.getContextData();
                } else {
                    if (this.contextData.isFrozen()) {
                        this.contextData = ContextDataFactory.createContextData();
                    } else {
                        this.contextData.clear();
                    }
                    this.contextData.putAll(other.getContextData());

                }
                this.thrownProxy = other.getThrownProxy();
                this.source = other.getSource();
                this.threadId = other.getThreadId();
                this.threadName = other.getThreadName();
                this.threadPriority = other.getThreadPriority();
            }
        }
