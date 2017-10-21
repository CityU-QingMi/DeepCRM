    @Override
    public List<Profile> getProfiles()
    {
        List<Profile> result = new ArrayList<>();
        for ( org.apache.maven.model.Profile profile : request.getProfiles() )
        {
            result.add( SettingsUtils.convertToSettingsProfile( profile ) );
        }
        return result;
    }
