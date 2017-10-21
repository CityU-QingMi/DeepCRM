    @Test
    public void testMergeModel_Profiles()
    {
        Model parent = new Model();
        parent.setProfiles( Collections.singletonList( new Profile() ) );;
        Model model = new Model();
        modelMerger.mergeModel_Profiles( model, parent, false, null );
        assertEquals( 0, model.getProfiles().size() );
        
        Profile modelProfile = new Profile();
        modelProfile.setId( "MODEL" );
        model.setProfiles( Collections.singletonList( modelProfile ) );
        modelMerger.mergeModel_Prerequisites( model, parent, false, null );
        assertEquals( Collections.singletonList( modelProfile ), model.getProfiles() );
    }
