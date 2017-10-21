    private static int toPatternFlags(final String[] patternFlags) throws IllegalArgumentException,
            IllegalAccessException {
        if (patternFlags == null || patternFlags.length == 0) {
            return DEFAULT_PATTERN_FLAGS;
        }
        final Field[] fields = Pattern.class.getDeclaredFields();
        final Comparator<Field> comparator = new Comparator<Field>() {

            @Override
            public int compare(final Field f1, final Field f2) {
                return f1.getName().compareTo(f2.getName());
            }
        };
        Arrays.sort(fields, comparator);
        final String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        int flags = DEFAULT_PATTERN_FLAGS;
        for (final String test : patternFlags) {
            final int index = Arrays.binarySearch(fieldNames, test);
            if (index >= 0) {
                final Field field = fields[index];
                flags |= field.getInt(Pattern.class);
            }
        }
        return flags;
    }
