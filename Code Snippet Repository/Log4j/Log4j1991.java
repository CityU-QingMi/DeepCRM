    private String throughputTable() {
        final StringBuilder sb = new StringBuilder(4 * 1024);
        final Set<String> subKeys = results.values().iterator().next().keySet();
        sb.append("\tThroughput per thread (msg/sec)");
        sb.append(LF);
        for (final String subKey : subKeys) {
            sb.append('\t').append(subKey);
        }
        sb.append(LF);
        for (final String key : results.keySet()) {
            sb.append(key);
            final Map<String, Stats> sub = results.get(key);
            for (final String subKey : sub.keySet()) {
                final Stats stats = sub.get(subKey);
                sb.append('\t').append(stats.throughput);
            }
            sb.append(LF);
        }
        return sb.toString();
    }
