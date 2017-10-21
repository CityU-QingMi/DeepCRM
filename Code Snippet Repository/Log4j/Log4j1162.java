        @Override
        public StringMap injectContextData(final List<Property> props, final StringMap ignore) {
            // If there are no configuration properties we want to just return the ThreadContext's StringMap:
            // it is a copy-on-write data structure so we are sure ThreadContext changes will not affect our copy.
            final StringMap immutableCopy = ThreadContext.getThreadContextMap().getReadOnlyContextData();
            if (props == null || props.isEmpty()) {
                return immutableCopy; // this will replace the LogEvent's context data with the returned instance
            }
            // However, if the list of Properties is non-empty we need to combine the properties and the ThreadContext
            // data. Note that we cannot reuse the specified StringMap: some Loggers may have properties defined
            // and others not, so the LogEvent's context data may have been replaced with an immutable copy from
            // the ThreadContext - this will throw an UnsupportedOperationException if we try to modify it.
            final StringMap result = ContextDataFactory.createContextData(props.size() + immutableCopy.size());
            copyProperties(props, result);
            result.putAll(immutableCopy);
            return result;
        }
