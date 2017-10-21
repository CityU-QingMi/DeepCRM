    @Test
    public void testSetAttributesForArrayTypeAttribue() throws Exception
    {
        // given
        derivedes = getArrayTypeAttribute();

        // when
        derivedManaged.setAddresses(derivedes);
        mBeanDerivedManaged.getMBeanInfo();

        // then
        assertNotNull("Address object shouldn't be null",mBeanDerivedManaged.getAttribute("addresses"));
    }
