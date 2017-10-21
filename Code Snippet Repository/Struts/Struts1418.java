    public void doTestAddingToMapsWithObjects(boolean allowAdditions) throws Exception {

        loadButAdd(ObjectTypeDeterminer.class, new MockObjectTypeDeterminer(Long.class,Cat.class,null,allowAdditions));

        Foo foo = new Foo();
        foo.setAnotherCatMap(new HashMap());
        String spielname = "Spielen";
        ValueStack vs = ActionContext.getContext().getValueStack();
        vs.getContext().put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);
        vs.getContext().put(ReflectionContextState.CREATE_NULL_OBJECTS, Boolean.TRUE);
        vs.push(foo);
        vs.getContext().put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);
        vs.setValue("anotherCatMap[\"3\"].name", spielname);
        Object setCat = foo.getAnotherCatMap().get(new Long(3));
        if (allowAdditions) {
            assertNotNull(setCat);
            assertTrue(setCat instanceof Cat);
            assertTrue(((Cat) setCat).getName().equals(spielname));
        }	else {
            assertNull(setCat);
        }


    }
