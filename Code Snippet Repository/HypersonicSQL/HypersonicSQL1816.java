    static int calcInterval(int segments, int start, int limit) {

        int range = limit - start;

        if (range <= 0) {
            return 0;
        }

        int partSegment = (range % segments) == 0 ? 0
                                                  : 1;

        return (range / segments) + partSegment;
    }
