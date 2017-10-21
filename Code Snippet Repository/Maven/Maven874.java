    private List<String> getProfileIds( List<Profile> profiles )
    {
        List<String> ids = new ArrayList<>( profiles.size() );

        for ( Profile profile : profiles )
        {
            ids.add( profile.getId() );
        }

        return ids;
    }
