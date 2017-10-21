        @Deprecated
        public Builder setContextMap(final Map<String, String> contextMap) {
            contextData = ContextDataFactory.createContextData(); // replace with new instance
            if (contextMap != null) {
                for (final Map.Entry<String, String> entry : contextMap.entrySet()) {
                    contextData.putValue(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }
