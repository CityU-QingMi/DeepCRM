    public static void main(final String[] args) throws Exception {
        final PerfTestResultFormatter fmt = new PerfTestResultFormatter();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            fmt.process(line);
        }
        System.out.println(fmt.latencyTable());
        System.out.println();
        System.out.println(fmt.throughputTable());
    }
