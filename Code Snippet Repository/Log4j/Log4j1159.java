        @Override
        public StringMap injectContextData(final List<Property> props, final StringMap ignore) {

            final Map<String, String> copy = ThreadContext.getImmutableContext();

            // The DefaultThreadContextMap stores context data in a Map<String, String>.
            // This is a copy-on-write data structure so we are sure ThreadContext changes will not affect our copy.
            // If there are no configuration properties returning a thin wrapper around the copy
            // is faster than copying the elements into the LogEvent's reusable StringMap.
            if (props == null || props.isEmpty()) {
                // this will replace the LogEvent's context data with the returned instance.
                // NOTE: must mark as frozen or downstream components may attempt to modify (UnsupportedOperationEx)
                return copy.isEmpty() ? ContextDataFactory.emptyFrozenContextData() : frozenStringMap(copy);
            }
            // If the list of Properties is non-empty we need to combine the properties and the ThreadContext
            // data. Note that we cannot reuse the specified StringMap: some Loggers may have properties defined
            // and others not, so the LogEvent's context data may have been replaced with an immutable copy from
            // the ThreadContext - this will throw an UnsupportedOperationException if we try to modify it.
            final StringMap result = new JdkMapAdapterStringMap(new HashMap<>(copy));
            for (int i = 0; i < props.size(); i++) {
                final Property prop = props.get(i);
                if (!copy.containsKey(prop.getName())) {
                    result.putValue(prop.getName(), prop.getValue());
                }
            }
            result.freeze();
            return result;
        }
