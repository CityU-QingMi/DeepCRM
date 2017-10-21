    private String latencyTable() {
        final StringBuilder sb = new StringBuilder(4 * 1024);
        final Set<String> subKeys = results.values().iterator().next().keySet();
        final char[] tabs = new char[subKeys.size()];
        Arrays.fill(tabs, '\t');
        final String sep = new String(tabs);
        sb.append("\tAverage latency").append(sep).append("99% less than").append(sep).append("99.99% less than");
        sb.append(LF);
        for (int i = 0; i < 3; i++) {
            for (final String subKey : subKeys) {
                sb.append('\t').append(subKey);
            }
        }
        sb.append(LF);
        for (final String key : results.keySet()) {
            sb.append(key);
            for (int i = 0; i < 3; i++) {
                final Map<String, Stats> sub = results.get(key);
                for (final String subKey : sub.keySet()) {
                    final Stats stats = sub.get(subKey);
                    switch (i) {
                    case 0:
                        sb.append('\t').append((long) stats.avgLatency);
                        break;
                    case 1:
                        sb.append('\t').append((long) stats.latency99Pct);
                        break;
                    case 2:
                        sb.append('\t').append((long) stats.latency99_99Pct);
                        break;
                    }
                }
            }
            sb.append(LF);
        }
        return sb.toString();
    }
