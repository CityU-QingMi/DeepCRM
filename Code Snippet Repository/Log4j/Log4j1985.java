        public Stats(final String raw) {
            final String[] lines = raw.split("[\\r\\n]+");
            long totalOps = 0;
            for (final String line : lines) {
                if (line.startsWith("avg")) {
                    latencyRowCount++;
                    final String[] parts = line.split(" ");
                    int i = 0;
                    average += Long.parseLong(parts[i++].split("=")[1]);
                    pct99 += Long.parseLong(parts[i++].split("=")[1]);
                    pct99_99 += Long.parseLong(parts[i++].split("=")[1]);
                    count += Integer.parseInt(parts[i].split("=")[1]);
                } else {
                    throughputRowCount++;
                    final String number = line.substring(0, line.indexOf(' '));
                    final long opsPerSec = Long.parseLong(number);
                    totalOps += opsPerSec;
                }
            }
            averageOpsPerSec = totalOps / throughputRowCount;
        }
