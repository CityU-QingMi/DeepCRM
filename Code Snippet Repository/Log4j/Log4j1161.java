        @Override
        public StringMap injectContextData(final List<Property> props, final StringMap reusable) {
            // When the ThreadContext is garbage-free, we must copy its key-value pairs into the specified reusable
            // StringMap. We cannot return the ThreadContext's internal data structure because it may be modified later
            // and such modifications should not be reflected in the log event.
            copyProperties(props, reusable);

            final ReadOnlyStringMap immutableCopy = ThreadContext.getThreadContextMap().getReadOnlyContextData();
            reusable.putAll(immutableCopy);
            return reusable;
        }
