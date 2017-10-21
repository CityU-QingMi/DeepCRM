    public static org.apache.maven.model.RepositoryPolicy fromSettingsRepositoryPolicy(
                                                 org.apache.maven.settings.RepositoryPolicy settingsRepositoryPolicy )
    {
        org.apache.maven.model.RepositoryPolicy modelRepositoryPolicy = new org.apache.maven.model.RepositoryPolicy();
        if ( settingsRepositoryPolicy != null )
        {
            modelRepositoryPolicy.setEnabled( settingsRepositoryPolicy.isEnabled() );
            modelRepositoryPolicy.setUpdatePolicy( settingsRepositoryPolicy.getUpdatePolicy() );
            modelRepositoryPolicy.setChecksumPolicy( settingsRepositoryPolicy.getChecksumPolicy() );
        }
        return modelRepositoryPolicy;
    }
