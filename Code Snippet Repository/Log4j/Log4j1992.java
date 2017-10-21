    private void process(final String line) throws ParseException {
        final String key = line.substring(line.indexOf('.') + 1, line.indexOf('('));
        final String sub = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
        final String throughput = line.substring(line.indexOf("throughput: ")
                + "throughput: ".length(), line.indexOf(" ops"));
        final String avg = line.substring(line.indexOf("avg=") + "avg=".length(),
                line.indexOf(" 99%"));
        final String pct99 = line.substring(
                line.indexOf("99% < ") + "99% < ".length(),
                line.indexOf(" 99.99%"));
        final String pct99_99 = line.substring(line.indexOf("99.99% < ")
                + "99.99% < ".length(), line.lastIndexOf('(') - 1);
        final Stats stats = new Stats(throughput, avg, pct99, pct99_99);
        Map<String, Stats> map = results.get(key.trim());
        if (map == null) {
            map = new TreeMap<>(sort());
            results.put(key.trim(), map);
        }
        String subKey = sub.trim();
        if ("single thread".equals(subKey)) {
            subKey = "1 thread";
        }
        map.put(subKey, stats);
    }
