    private final Path findJettyBasePath()
    {
        // If a jetty property is defined, use it
        Prop prop = this.props.getProp(BaseHome.JETTY_BASE,false);
        if (prop != null && !Utils.isBlank(prop.value))
        {
            return FS.toPath(prop.value);
        }

        // If a system property is defined, use it
        String val = System.getProperty(BaseHome.JETTY_BASE);
        if (!Utils.isBlank(val))
        {
            setProperty(BaseHome.JETTY_BASE,val,ORIGIN_SYSTEM_PROPERTY);
            return FS.toPath(val);
        }

        // Lastly, fall back to base == ${user.dir}
        Path base = FS.toPath(this.props.getString("user.dir","."));
        setProperty(BaseHome.JETTY_BASE,base.toString(),ORIGIN_INTERNAL_FALLBACK);
        return base;
    }
