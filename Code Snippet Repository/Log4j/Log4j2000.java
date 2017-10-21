    private static void printReport(final String label, final long[] UPTIMES, final long[] DURATIONS,
            final int offset, final int length) {
        final StringBuilder sb = new StringBuilder(512);
        long total = 0;
        for (int i = offset; i < offset + length; i++) {
            sb.setLength(0);
            final long opsPerSec = (1000L * 1000L * 1000L * ITERATIONS) / DURATIONS[i];
            total += opsPerSec;
            sb.append(UPTIMES[i]).append(" ");
            sb.append(label).append(": Throughput: ").append(opsPerSec).append(" ops/s");
            System.out.println(sb);
        }
        sb.setLength(0);
        sb.append("Average ").append(label).append(" throughput: ").append(total/length).append(" ops/s");
        System.out.println(sb);

        sb.setLength(0);
        System.out.println(sb.append(label).append(" ran: ").append(length).append(" iterations"));
    }
