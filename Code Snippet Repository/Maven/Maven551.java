    public static org.apache.maven.model.Repository fromSettingsRepository( org.apache.maven.settings.Repository
                                                                            settingsRepository )
    {
        org.apache.maven.model.Repository modelRepository = new org.apache.maven.model.Repository();
        modelRepository.setId( settingsRepository.getId() );
        modelRepository.setLayout( settingsRepository.getLayout() );
        modelRepository.setName( settingsRepository.getName() );
        modelRepository.setUrl( settingsRepository.getUrl() );
        modelRepository.setReleases( fromSettingsRepositoryPolicy( settingsRepository.getReleases() ) );
        modelRepository.setSnapshots( fromSettingsRepositoryPolicy( settingsRepository.getSnapshots() ) );
        return modelRepository;
    }
