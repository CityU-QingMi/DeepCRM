    public void testAddingToCollectionBasedOnPermission() {
        final MockObjectTypeDeterminer determiner = new MockObjectTypeDeterminer(Long.class,Bar.class,"id",true);
        loadConfigurationProviders(new StubConfigurationProvider() {
            @Override
            public void register(ContainerBuilder builder,
                    LocatableProperties props) throws ConfigurationException {
                builder.factory(ObjectTypeDeterminer.class, new Factory() {
                    public Object create(Context context) throws Exception {
                        return determiner;
                    }
                    
                }, Scope.SINGLETON);
            }
        });

        Collection barColl=new HashSet();

        ValueStack vs = ActionContext.getContext().getValueStack();
        ReflectionContextState.setCreatingNullObjects(vs.getContext(), true);
        ReflectionContextState.setReportingConversionErrors(vs.getContext(), true);
        Foo foo = new Foo();

        foo.setBarCollection(barColl);

        vs.push(foo);

        String bar1Title="title";
        vs.setValue("barCollection(11).title", bar1Title);

        assertEquals(1, barColl.size());
        Object bar=barColl.iterator().next();
        assertTrue(bar instanceof Bar);
        assertEquals(((Bar)bar).getTitle(), bar1Title);
        assertEquals(((Bar)bar).getId(), new Long(11));

        //now test where there is no permission
        determiner.setShouldCreateIfNew(false);

        String bar2Title="another title";
        vs.setValue("barCollection(22).title", bar1Title);

        assertEquals(1, barColl.size());
        bar=barColl.iterator().next();
        assertTrue(bar instanceof Bar);
        assertEquals(((Bar)bar).getTitle(), bar1Title);
        assertEquals(((Bar)bar).getId(), new Long(11));


    }
