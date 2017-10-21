    public static int rank(int[] array, int elements, int target, int start,
                           int limit, int margin) {

        final int segments     = 256;
        int       elementCount = 0;
        int       currentLimit = limit;

        for (;;) {
            int interval = calcInterval(segments, start, currentLimit);
            int[] counts = countSegments(array, elements, segments, interval,
                                         start, currentLimit);

            for (int i = 0; i < counts.length; i++) {
                if (elementCount + counts[i] < target) {
                    elementCount += counts[i];
                    start        += interval;
                } else {
                    break;
                }
            }

            if (elementCount + margin >= target) {
                return start;
            }

            if (interval <= 1) {
                return start;
            }

            currentLimit = start + interval < limit ? (start + interval)
                                                    : limit;
        }
    }
