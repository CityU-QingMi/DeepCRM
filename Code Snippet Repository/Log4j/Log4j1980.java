    static String createSamplingReport(final String name, final Histogram histogram) {
        final Histogram data = histogram;
        if (throughput) {
            return data.getMax() + " operations/second";
        }
        final String result = String.format("avg=%.0f 99%%=%d 99.99%%=%d sampleCount=%d", //
                data.getMean(), //
                data.getTwoNinesUpperBound(), //
                data.getFourNinesUpperBound(), //
                data.getCount() //
                );
        return result;
    }
