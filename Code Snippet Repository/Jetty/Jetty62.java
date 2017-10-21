    @Override
    public void init()
    {
        if (JavaVersion.VERSION.getPlatform()!=8)
            throw new IllegalStateException(this + " not applicable for java "+JavaVersion.VERSION);
        if (ALPN.class.getClassLoader()!=null)
            throw new IllegalStateException(ALPN.class.getName() + " must be on JVM boot classpath");
        if (LOG.isDebugEnabled())
            ALPN.debug = true;
    }
