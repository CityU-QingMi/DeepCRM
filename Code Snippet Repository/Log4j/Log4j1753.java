    @Test
    public void sendLargeLegacyBsdMessageOverTls() throws IOException, InterruptedException {
        final String prefix = "BEGIN";
        initTlsTestEnvironment(1, TlsSyslogMessageFormat.LEGACY_BSD);

        final char[] msg = new char[2 * 1024 * 2014 + prefix.length()];
        Arrays.fill(msg, 'a');
        System.arraycopy(prefix.toCharArray(), 0, msg, 0, prefix.length());
        sendAndCheckLegacyBsdMessage(new String(msg));
    }
