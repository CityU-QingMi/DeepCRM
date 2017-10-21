    @Parameterized.Parameters(name = "")
    public static List<Object[]> data()
    {
        List<Object[]> data = new ArrayList<>();
    
        String arch = String.format("%s/%s", System.getProperty("os.name"), System.getProperty("os.arch"));
        
        String title;
        Map<String, String> env;
        
        // ------
        title = "Typical Setup";
        
        env = new HashMap<>();
        env.put("jetty.home", asTargetPath(title,"jetty-distro"));
        env.put("jetty.base", asTargetPath(title,"jetty-distro/demo.base"));
        env.put("WAR", asTargetPath(title,"jetty-distro/demo.base/webapps/FOO"));
        
        data.add(new Object[]{arch, title, env});
        
        // ------
        // This puts the jetty.home inside of the jetty.base
        title = "Overlap Setup";
        env = new HashMap<>();
        env.put("jetty.home", asTargetPath(title,"app/dist"));
        env.put("jetty.base", asTargetPath(title,"app"));
        env.put("WAR", asTargetPath(title,"app/webapps/FOO"));
        
        data.add(new Object[]{arch, title, env});
        
        // ------
        // This tests a path scenario often seen on various automatic deployments tooling
        // such as Kubernetes, CircleCI, TravisCI, and Jenkins.
        title = "Nasty Path Setup";
        env = new HashMap<>();
        env.put("jetty.home", asTargetPath(title,"app%2Fnasty/dist"));
        env.put("jetty.base", asTargetPath(title,"app%2Fnasty/base"));
        env.put("WAR", asTargetPath(title,"app%2Fnasty/base/webapps/FOO"));
        
        data.add(new Object[]{arch, title, env});
        return data;
    }
