    @Parameters
    public static Collection<Object[]> data()
    {
        List<Object[]> data = new ArrayList<Object[]>();
        
        double javaVersion = Double.parseDouble(System.getProperty("java.specification.version"));

        // @formatter:off
        data.add(new Object[] { "/dump.jsp" });
        data.add(new Object[] { "/dump.jsp/" });
        data.add(new Object[] { "/dump.jsp%00" });
        data.add(new Object[] { "/dump.jsp%00x" });
        data.add(new Object[] { "/dump.jsp%00x/dump.jsp" });
        data.add(new Object[] { "/dump.jsp%00/dump.jsp" });
        data.add(new Object[] { "/dump.jsp%00/index.html" });

        if (javaVersion >= 1.7) 
        {
            data.add(new Object[] { "/dump.jsp%00/" });
            data.add(new Object[] { "/dump.jsp%00x/" });
        }
        // @formatter:on

        return data;
    }
