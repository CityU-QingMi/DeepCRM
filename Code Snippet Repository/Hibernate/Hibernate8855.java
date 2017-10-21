    @Test
    @TestForIssue( jiraKey = "")
    public void testNaturalIdNullability() {
        final EntityPersister persister = sessionFactory().getEntityPersister( Child.class.getName() );
        // nullability is not specified for either properties making up
        // the natural ID, so they should be non-nullable by hbm-specific default
        final EntityMetamodel entityMetamodel = persister.getEntityMetamodel();
        assertFalse( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "parent" )] );
        assertFalse( persister.getPropertyNullability()[entityMetamodel.getPropertyIndex( "name" )] );
    }
