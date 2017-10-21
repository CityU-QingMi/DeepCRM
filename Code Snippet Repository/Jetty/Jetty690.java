    private void assumeJavaVersionSupportsTLSRenegotiations()
    {
        // Due to a security bug, TLS renegotiations were disabled in JDK 1.6.0_19-21
        // so we check the java version in order to avoid to fail the test.
        String javaVersion = System.getProperty("java.version");
        Pattern regexp = Pattern.compile("1\\.6\\.0_(\\d{2})");
        Matcher matcher = regexp.matcher(javaVersion);
        if (matcher.matches())
        {
            String nano = matcher.group(1);
            Assume.assumeThat(Integer.parseInt(nano), Matchers.greaterThan(21));
        }
    }
