        public StructuredDataElement format(final LogEvent event) {
            final Map<String, String> map = new HashMap<>(delegateMap.size());

            for (final Map.Entry<String, List<PatternFormatter>> entry : delegateMap.entrySet()) {
                final StringBuilder buffer = new StringBuilder();
                for (final PatternFormatter formatter : entry.getValue()) {
                    formatter.format(event, buffer);
                }
                map.put(entry.getKey(), buffer.toString());
            }
            return new StructuredDataElement(map, eventPrefix, discardIfEmpty);
        }
