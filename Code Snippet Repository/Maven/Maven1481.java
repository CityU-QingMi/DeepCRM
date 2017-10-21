    @Override
    public List<Profile> getActiveProfiles( Collection<Profile> profiles, ProfileActivationContext context,
                                            ModelProblemCollector problems )
    {
        Collection<String> activatedIds = new HashSet<>( context.getActiveProfileIds() );
        Collection<String> deactivatedIds = new HashSet<>( context.getInactiveProfileIds() );

        List<Profile> activeProfiles = new ArrayList<>( profiles.size() );
        List<Profile> activePomProfilesByDefault = new ArrayList<>();
        boolean activatedPomProfileNotByDefault = false;

        for ( Profile profile : profiles )
        {
            if ( !deactivatedIds.contains( profile.getId() ) )
            {
                if ( activatedIds.contains( profile.getId() ) || isActive( profile, context, problems ) )
                {
                    activeProfiles.add( profile );

                    if ( Profile.SOURCE_POM.equals( profile.getSource() ) )
                    {
                        activatedPomProfileNotByDefault = true;
                    }
                }
                else if ( isActiveByDefault( profile ) )
                {
                    if ( Profile.SOURCE_POM.equals( profile.getSource() ) )
                    {
                        activePomProfilesByDefault.add( profile );
                    }
                    else
                    {
                        activeProfiles.add( profile );
                    }
                }

            }
        }

        if ( !activatedPomProfileNotByDefault )
        {
            activeProfiles.addAll( activePomProfilesByDefault );
        }

        return activeProfiles;
    }
