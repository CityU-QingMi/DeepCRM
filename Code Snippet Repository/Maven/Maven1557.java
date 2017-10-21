    @Test
    public void testMergeModel_ModelVersion()
    {
        Model parent = new Model();
        parent.setModelVersion( "4.0.0" );
        Model model = new Model();
        modelMerger.mergeModel_ModelVersion( model, parent, false, null );
        assertNull( model.getModelVersion() );

        model.setModelVersion( "5.0.0" );
        modelMerger.mergeModel_ModelVersion( model, parent, false, null );
        assertEquals( "5.0.0", model.getModelVersion() );
    }
