    private final Path findJettyHomePath()
    {
        // If a jetty property is defined, use it
        Prop prop = this.props.getProp(BaseHome.JETTY_HOME,false);
        if (prop != null && !Utils.isBlank(prop.value))
        {
            return FS.toPath(prop.value);
        }

        // If a system property is defined, use it
        String val = System.getProperty(BaseHome.JETTY_HOME);
        if (!Utils.isBlank(val))
        {
            setProperty(BaseHome.JETTY_HOME,val,ORIGIN_SYSTEM_PROPERTY);
            return FS.toPath(val);
        }

        // Attempt to find path relative to content in jetty's start.jar
        // based on lookup for the Main class (from jetty's start.jar)
        String classRef = "org/eclipse/jetty/start/Main.class";
        URL jarfile = this.getClass().getClassLoader().getResource(classRef);
        if (jarfile != null)
        {
            Matcher m = Pattern.compile("jar:(file:.*)!/" + classRef).matcher(jarfile.toString());
            if (m.matches())
            {
                // ${jetty.home} is relative to found BaseHome class
                try
                {
                    Path home = new File(new URI(m.group(1))).getParentFile().toPath();
                    setProperty(BaseHome.JETTY_HOME,home.toString(),ORIGIN_INTERNAL_FALLBACK);
                    return home;
                }
                catch (URISyntaxException e)
                {
                    throw new UsageException(UsageException.ERR_UNKNOWN,e);
                }
            }
        }

        // Lastly, fall back to ${user.dir} default
        Path home = FS.toPath(System.getProperty("user.dir","."));
        setProperty(BaseHome.JETTY_HOME,home.toString(),"<user.dir>");
        return home;
    }
