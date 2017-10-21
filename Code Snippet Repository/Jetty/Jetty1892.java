    @Test(expected = AttributeNotFoundException.class)
    public void testSetAttributeAttributeWithWrongAttrName() throws Exception
    {
        // given
        attribute = new Attribute("fnameee","charu");

        // when
        objectMBean.setAttribute(attribute);

        // then
        fail("An AttributeNotFoundException must have occured by now as there is no attribute " + "with the name ffname in bean");
    }
