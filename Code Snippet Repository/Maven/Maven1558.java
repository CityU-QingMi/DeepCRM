    @Test
    public void testMergeModel_ArtifactId()
    {
        Model parent = new Model();
        parent.setArtifactId( "PARENT" );
        Model model = new Model();
        modelMerger.mergeModel_ArtifactId( model, parent, false, null );
        assertNull( model.getArtifactId() );

        model.setArtifactId( "MODEL" );
        modelMerger.mergeModel_ArtifactId( model, parent, false, null );
        assertEquals( "MODEL", model.getArtifactId() );
    }
