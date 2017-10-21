    public void merge( ArtifactRepositoryPolicy policy )
    {
        if ( policy != null && policy.isEnabled() )
        {
            setEnabled( true );

            if ( ordinalOfCksumPolicy( policy.getChecksumPolicy() ) < ordinalOfCksumPolicy( getChecksumPolicy() ) )
            {
                setChecksumPolicy( policy.getChecksumPolicy() );
            }

            if ( ordinalOfUpdatePolicy( policy.getUpdatePolicy() ) < ordinalOfUpdatePolicy( getUpdatePolicy() ) )
            {
                setUpdatePolicy( policy.getUpdatePolicy() );
            }
        }
    }
