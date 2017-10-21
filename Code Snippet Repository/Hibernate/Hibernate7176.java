    @Test
    public void test() {
        doInHibernate( this::sessionFactory, s -> {
            Child loadedChild = s.load( Child.class, lastChildID );

            Object nameByReflection = getFieldByReflection( loadedChild, "name" );
            assertNotNull( "Non-lazy field 'name' was not loaded", nameByReflection );

            Object parentByReflection = getFieldByReflection( loadedChild, "parent" );
            assertNull( "Lazy field 'parent' is initialized", parentByReflection );
            assertFalse( loadedChild instanceof HibernateProxy );

            Parent loadedParent = loadedChild.parent;
            assertThat( loadedChild.name, notNullValue() );
            assertThat( loadedParent, notNullValue() );
            assertThat( loadedChild.parent, notNullValue() );

            checkDirtyTracking( loadedChild );

            parentByReflection = getFieldByReflection( loadedChild, "parent" );
            Object childrenByReflection = getFieldByReflection( loadedParent, "children" );
            assertNotNull( "Lazy field 'parent' is not loaded", parentByReflection );
            assertNull( "Lazy field 'children' is initialized", childrenByReflection );
            assertFalse( loadedParent instanceof HibernateProxy );
            assertEquals( parentID, loadedParent.id );

            Collection<Child> loadedChildren = loadedParent.children;

            checkDirtyTracking( loadedChild );
            checkDirtyTracking( loadedParent );

            childrenByReflection = getFieldByReflection( loadedParent, "children" );
            assertNotNull( "Lazy field 'children' is not loaded", childrenByReflection );
            assertFalse( loadedChildren instanceof HibernateProxy );
            assertEquals( CHILDREN_SIZE, loadedChildren.size() );
            assertTrue( loadedChildren.contains( loadedChild ) );
        } );
    }
