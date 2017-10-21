    private static Thread printProcessOutput(final Process process, final boolean[] stop) {

        final Thread t = new Thread("OutputWriter") {
            @Override
            public void run() {
                final BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                try {
                    String line = null;
                    while (!stop[0] && (line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (final Exception ignored) {
                }
            }
        };
        t.start();
        return t;
    }
