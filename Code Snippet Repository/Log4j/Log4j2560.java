    public void testUsageMainCommand()  {
        CommandLine commandLine = mainCommand();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            commandLine.usage(new PrintStream(baos, true, "UTF8"));
            String result = baos.toString("UTF8");
            System.out.println(result);
            assert String.format(EXPECTED_USAGE_MAIN).equals(result);
        } catch (UnsupportedEncodingException ex) {
            throw new InternalError(ex.toString());
        }
    }
