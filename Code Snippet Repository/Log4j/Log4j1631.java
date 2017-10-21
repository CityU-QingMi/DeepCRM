    public static void runTest(final Class<?> cls) throws Exception {
        final String javaHome = System.getProperty("java.home");
        final String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        final String classpath = System.getProperty("java.class.path");
        final String javaagent = "-javaagent:" + agentJar();

        final File tempFile = File.createTempFile("allocations", ".txt");
        tempFile.deleteOnExit();

        final ProcessBuilder builder = new ProcessBuilder( //
                javaBin, javaagent, "-cp", classpath, cls.getName());
        builder.redirectError(ProcessBuilder.Redirect.to(tempFile));
        builder.redirectOutput(ProcessBuilder.Redirect.to(tempFile));
        final Process process = builder.start();
        process.waitFor();
        process.exitValue();

        final String text = new String(Files.readAllBytes(tempFile.toPath()));
        final List<String> lines = Files.readAllLines(tempFile.toPath(), Charset.defaultCharset());
        final String className = cls.getSimpleName();
        assertEquals(text, "FATAL o.a.l.l.c." + className + " [main] value1 {aKey=value1, key2=value2, prop1=value1, prop2=value2} This message is logged to the console",
                lines.get(0));

        for (int i = 1; i < lines.size(); i++) {
            final String line = lines.get(i);
            assertFalse(i + ": " + line + Strings.LINE_SEPARATOR + text, line.contains("allocated") || line.contains("array"));
        }
    }
