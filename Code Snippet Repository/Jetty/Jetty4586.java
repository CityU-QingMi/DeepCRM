    public StartIniBuilder(BaseBuilder baseBuilder) throws IOException
    {
        this.baseHome = baseBuilder.getBaseHome();
        this.startIni = baseHome.getBasePath("start.ini");

        if (Files.exists(startIni))
        {
            parseIni();
        }
    }
