    @Override
    public void setConfig(ExtensionConfig config)
    {
        super.setConfig(config);

        String cfgOutputDir = config.getParameter("output-dir",null);
        if (StringUtil.isNotBlank(cfgOutputDir))
        {
            Path path = new File(cfgOutputDir).toPath();
            if (Files.isDirectory(path) && Files.exists(path) && Files.isWritable(path))
            {
                this.outputDir = path;
            }
            else
            {
                LOG.warn("Unable to configure {}: not a valid output directory",path.toAbsolutePath().toString());
            }
        }

        String cfgPrefix = config.getParameter("prefix","frame");
        if (StringUtil.isNotBlank(cfgPrefix))
        {
            this.prefix = cfgPrefix;
        }

        if (this.outputDir != null)
        {
            try
            {
                Path dir = this.outputDir.toRealPath();

                // create a non-validating, read-only generator
                String tstamp = String.format("%1$tY%1$tm%1$td-%1$tH%1$tM%1$tS",Calendar.getInstance());
                incomingFramesPath = dir.resolve(String.format("%s-%s-incoming.dat",this.prefix,tstamp));
                outgoingFramesPath = dir.resolve(String.format("%s-%s-outgoing.dat",this.prefix,tstamp));

                incomingChannel = Files.newByteChannel(incomingFramesPath,CREATE,WRITE);
                outgoingChannel = Files.newByteChannel(outgoingFramesPath,CREATE,WRITE);

                this.generator = new Generator(WebSocketPolicy.newServerPolicy(),getBufferPool(),false,true);
            }
            catch (IOException e)
            {
                LOG.warn("Unable to create capture file(s)",e);
            }
        }
    }
