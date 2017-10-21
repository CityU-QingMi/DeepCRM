        public Instance putAll(final Map<String, String> values) {
            final Map<String, String> currentValues = ThreadContext.getContext();
            ThreadContext.putAll(values);
            for (final String key : values.keySet()) {
                if (!originalValues.containsKey(key)) {
                    originalValues.put(key, currentValues.get(key));
                }
            }
            return this;
        }
