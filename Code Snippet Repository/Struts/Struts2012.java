    public void testValueStackMode() throws Exception {
        ScopesHashModel model = new ScopesHashModel(ObjectWrapper.BEANS_WRAPPER, null, null, ActionContext.getContext().getValueStack());

        CollectionModel stringList = null;

        stringList = (CollectionModel) model.get("stringList");
        assertEquals("one", stringList.get(0).toString());

        assertEquals("one", model.get("stringList[0]").toString());
        assertEquals("one", model.get("beanList[0].name").toString());
    }
