    @Parameters(name="")
    public static List<String[]> data()
    {
        List<String[]> cases = new ArrayList<>();
        
        if (OS.IS_UNIX)
        {
            // absolute first
            cases.add(new String[]{"/opt/app/*.jar","/opt/app"});
            cases.add(new String[]{"/lib/jvm/**/jre/lib/*.jar","/lib/jvm"});
            cases.add(new String[]{"glob:/var/lib/*.xml","/var/lib"});
            cases.add(new String[]{"glob:/var/lib/*.{xml,java}","/var/lib"});
            cases.add(new String[]{"glob:/opt/corporate/lib-{dev,prod}/*.ini","/opt/corporate"});
            cases.add(new String[]{"regex:/opt/jetty/.*/lib-(dev|prod)/*.ini","/opt/jetty"});

            cases.add(new String[]{"/*.ini","/"});
            cases.add(new String[]{"/etc/jetty.conf","/etc"});
            cases.add(new String[]{"/common.conf","/"});
        }

        if (OS.IS_WINDOWS)
        {
            // absolute declaration
            cases.add(new String[]{"D:\\code\\jetty\\jetty-start\\src\\test\\resources\\extra-libs\\example.jar",
                    "D:\\code\\jetty\\jetty-start\\src\\test\\resources\\extra-libs"});
            // escaped declaration
            // absolute patterns (complete with required windows slash escaping)
            cases.add(new String[]{"C:\\\\corp\\\\lib\\\\*.jar","C:\\corp\\lib"});
            cases.add(new String[]{"D:\\\\lib\\\\**\\\\jre\\\\lib\\\\*.jar","D:\\lib"});
        }

        // some relative paths
        cases.add(new String[]{"lib/*.jar","lib"});
        cases.add(new String[]{"etc/jetty.xml","etc"});
        cases.add(new String[]{"start.ini","."});
        cases.add(new String[]{"start.d/","start.d"});
        return cases;
    }
