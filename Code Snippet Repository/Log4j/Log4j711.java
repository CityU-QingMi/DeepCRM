        @Override
        public ColumnMapping build() {
            if (pattern != null) {
                layout = PatternLayout.newBuilder()
                    .withPattern(pattern)
                    .withConfiguration(configuration)
                    .build();
            }
            if (!(layout != null
                || literal != null
                || Date.class.isAssignableFrom(type)
                || ReadOnlyStringMap.class.isAssignableFrom(type)
                || ThreadContextMap.class.isAssignableFrom(type)
                || ThreadContextStack.class.isAssignableFrom(type))) {
                LOGGER.error("No layout or literal value specified and type ({}) is not compatible with " +
                    "ThreadContextMap, ThreadContextStack, or java.util.Date", type);
                return null;
            }
            return new ColumnMapping(name, layout, literal, type);
        }
