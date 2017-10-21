    @Test
    @Ignore
    public void testMultipleVMs() throws Exception {
        final String classPath = System.getProperty("java.class.path");
        final Integer logEventCount = 10;
        final int processCount = 3;
        final Process[] processes = new Process[processCount];
        final ProcessBuilder[] builders = new ProcessBuilder[processCount];
        for (int index = 0; index < processCount; ++index) {
            builders[index] = new ProcessBuilder("java", "-cp", classPath, ProcessTest.class.getName(),
                    "Process " + index, logEventCount.toString(), "true", Boolean.toString(createOnDemand));
        }
        for (int index = 0; index < processCount; ++index) {
            processes[index] = builders[index].start();
        }
        for (int index = 0; index < processCount; ++index) {
            final Process process = processes[index];
            // System.out.println("Process " + index + " exited with " + p.waitFor());
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
            process.destroy();
        }
        verifyFile(logEventCount * processCount);
    }
