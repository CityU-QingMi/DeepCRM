    @Test
    public void testSetAttributesException()
    {
        // given
        attributes = getAttributes("fnameee","charu");

        // when
        attributes = objectMBean.setAttributes(attributes);

        // then
        // Original code eating the exception and returning zero size list
        assertEquals("As there is no attribute with the name fnameee, this should return empty",EMPTY,attributes.size());
    }
