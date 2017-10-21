    public static int main( String[] args, ClassWorld classWorld )
    {
        MavenCli cli = new MavenCli();

        MessageUtils.systemInstall();
        int result = cli.doMain( new CliRequest( args, classWorld ) );
        MessageUtils.systemUninstall();

        return result;
    }
