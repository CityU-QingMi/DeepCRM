    public SettingsAdapter( MavenExecutionRequest request )
    {
        this.request = request;

/**/
/**/
/**/
/**/
/**/
        File userSettings = request.getUserSettingsFile();
        this.runtimeInfo = new RuntimeInfo( ( userSettings != null && userSettings.isFile() ) ? userSettings : null );
    }
