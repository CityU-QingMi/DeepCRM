    @Parameters
    public static Collection<String[]> data()
    {
        List<String[]> data = new ArrayList<String[]>();

        double javaVersion = Double.parseDouble(System.getProperty("java.specification.version"));

        // @formatter:off
        data.add(new String[] { "false","/dump.jsp" });
        data.add(new String[] { "false","/dump.jsp/" });
        data.add(new String[] { "true", "/dump.jsp%00" });
        data.add(new String[] { "false","/dump.jsp%00/" });
        data.add(new String[] { "false","/dump.jsp%00x/dump.jsp" });
        data.add(new String[] { "false","/dump.jsp%00/dump.jsp" });

        if (javaVersion >= 1.7)
        {
            data.add(new String[] { "false","/dump.jsp%00x" });
            data.add(new String[] { "false","/dump.jsp%00x/" });
            data.add(new String[] { "false","/dump.jsp%00/index.html" });
        }
        // @formatter:on

        return data;
    }
