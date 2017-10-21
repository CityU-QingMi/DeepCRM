    public PersistedToolchains build( File userToolchainsFile )
        throws MisconfiguredToolchainException
    {
        PersistedToolchains toolchains = null;

        if ( userToolchainsFile != null && userToolchainsFile.isFile() )
        {
            try ( Reader in = ReaderFactory.newXmlReader( userToolchainsFile ) )
            {
                toolchains = new MavenToolchainsXpp3Reader().read( in );
            }
            catch ( Exception e )
            {
                throw new MisconfiguredToolchainException(
                    "Cannot read toolchains file at " + userToolchainsFile.getAbsolutePath(), e );
            }

        }
        else if ( userToolchainsFile != null )
        {
            logger.debug( "Toolchains configuration was not found at " + userToolchainsFile );
        }

        return toolchains;
    }
