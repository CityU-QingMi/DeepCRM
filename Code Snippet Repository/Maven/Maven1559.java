    @Test
    public void testMergeModel_Prerequisites()
    {
        Model parent = new Model();
        parent.setPrerequisites( new Prerequisites() );
        Model model = new Model();
        modelMerger.mergeModel_Prerequisites( model, parent, false, null );
        assertNull( model.getPrerequisites() );
        
        Prerequisites modelPrerequisites = new Prerequisites();
        modelPrerequisites.setMaven( "3.0" );
        model.setPrerequisites( modelPrerequisites );
        modelMerger.mergeModel_Prerequisites( model, parent, false, null );
        assertEquals( modelPrerequisites, model.getPrerequisites() );
    }
