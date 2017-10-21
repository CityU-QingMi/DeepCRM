    @Test
    public void testSetAttributesForCollectionTypeAttribue() throws Exception
    {
        // given
        aliasNames = getCollectionTypeAttribute();

        // when
        derivedManaged.setAliasNames(aliasNames);
        mBeanDerivedManaged.getMBeanInfo();

        // then
        assertNotNull("Address object shouldn't be null",mBeanDerivedManaged.getAttribute("aliasNames"));
        assertNull("Derived object shouldn't registerd with container so its value will be null",mBeanDerivedManaged.getAttribute("derived"));
    }
