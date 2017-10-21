    public void testSelectHelper() {
        StrutsUtil wwUtil = new StrutsUtil(ActionContext.getContext().getValueStack(), null, null);
        List selectList = null;

        selectList = wwUtil.makeSelectList("ignored", "stringList", null, null);
        assertEquals("one", ((ListEntry) selectList.get(0)).getKey());
        assertEquals("one", ((ListEntry) selectList.get(0)).getValue());

        selectList = wwUtil.makeSelectList("ignored", "beanList", "name", "value");
        assertEquals("one", ((ListEntry) selectList.get(0)).getKey());
        assertEquals("1", ((ListEntry) selectList.get(0)).getValue());
    }
