    @Test(expected = AttributeNotFoundException.class)
    public void testSetAttributeNullCheck() throws Exception
    {
        // given
        objectMBean.setAttribute(null);

        // when
        objectMBean.getAttribute(null);

        // then
        fail("An AttributeNotFoundException must have occured by now as there is no attribute with the name null");
    }
