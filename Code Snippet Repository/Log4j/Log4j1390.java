    public static PatternConverter newInstance(final String[] options) {
        if (options == null || options.length == 0) {
            return DatePatternConverter.newInstance(
                new String[]{
                    "yyyy-MM-dd"
                });
        }

        return DatePatternConverter.newInstance(options);
    }
