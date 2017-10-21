        public static List<LevelInfo> parse(final List<String> values, final Class<?> generator) {
            final List<LevelInfo> result = new ArrayList<>(values.size());
            for (int i = 0; i < values.size(); i++) {
                try {
                    result.add(new LevelInfo(values.get(i)));
                } catch (final Exception ex) {
                    System.err.println("Cannot parse custom level '" + values.get(i) + "': " + ex.toString());
                    usage(System.err, generator);
                    System.exit(-1);
                }
            }
            return result;
        }
