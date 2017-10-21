    @Parameters(name="")
    public static List<Object[]> data()
    {
        List<Object[]> cases = new ArrayList<>();
        
        if(OS.IS_UNIX)
        {
            cases.add(new Object[]{"/opt/app",true});
            cases.add(new Object[]{"/opt/app",true});
            cases.add(new Object[]{"/opt/florb",true});
            cases.add(new Object[]{"/home/user/benfranklin",true});
            cases.add(new Object[]{"glob:/home/user/benfranklin/*.jar",true});
            cases.add(new Object[]{"glob:/**/*.jar",true});
            cases.add(new Object[]{"regex:/*-[^dev].ini",true});
        }
        
        if(OS.IS_WINDOWS)
        {
            // normal declaration
            cases.add(new Object[]{"D:\\code\\jetty\\jetty-start\\src\\test\\resources\\extra-libs\\example.jar",true});
            // escaped declaration
            cases.add(new Object[]{"C:\\\\System32",true});
            cases.add(new Object[]{"C:\\\\Program Files",true});
        }
        
        cases.add(new Object[]{"etc",false});
        cases.add(new Object[]{"lib",false});
        cases.add(new Object[]{"${user.dir}",false});
        cases.add(new Object[]{"**/*.jar",false});
        cases.add(new Object[]{"glob:*.ini",false});
        cases.add(new Object[]{"regex:*-[^dev].ini",false});

        return cases;
    }
