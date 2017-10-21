    private Comparator<String> sort() {
        return new Comparator<String>() {
            List<String> expected = Arrays.asList("1 thread", "2 threads",
                    "4 threads", "8 threads", "16 threads", "32 threads",
                    "64 threads");

            @Override
            public int compare(final String o1, final String o2) {
                final int i1 = expected.indexOf(o1);
                final int i2 = expected.indexOf(o2);
                if (i1 < 0 || i2 < 0) {
                    return o1.compareTo(o2);
                }
                return i1 - i2;
            }
        };
    }
