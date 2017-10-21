    @Test
    public void testDeserializationOfUnknownClass() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "avalue");
        original.putValue("serializableButNotInClasspathOfDeserializer", new org.junit.runner.Result());
        original.putValue("zz", "last");

        final File file = new File("target/SortedArrayStringMap.ser");
        try (FileOutputStream fout = new FileOutputStream(file, false)) {
            fout.write(serialize(original));
            fout.flush();
        }
        final String classpath = createClassPath(SortedArrayStringMap.class, DeserializerHelper.class);
        final Process process = new ProcessBuilder("java", "-cp", classpath,
                DeserializerHelper.class.getName(), file.getPath()).start();
        final BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        final int exitValue = process.waitFor();

        file.delete();
        if (exitValue != 0) {
            final StringBuilder sb = new StringBuilder();
            sb.append("DeserializerHelper exited with error code ").append(exitValue);
            sb.append(". Classpath='").append(classpath);
            sb.append("'. Process output: ");
            int c = -1;
            while ((c = in.read()) != -1) {
                sb.append((char) c);
            }
            fail(sb.toString());
        }
    }
