    static void reportResult(final String file, final String name, final Histogram histogram) throws IOException {
        final String result = createSamplingReport(name, histogram);
        println(result);

        if (file != null) {
            try (final FileWriter writer = new FileWriter(file, true)) {
                writer.write(result);
                writer.write(System.lineSeparator());
            }
        }
    }
