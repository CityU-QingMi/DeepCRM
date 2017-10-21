    @Test
    public void testBasicOperations()
    {
        assertEquals("Managed objects should be equal",derivedExtended,objectMBean.getManagedObject());
        assertNull("This method call always returns null in the actual code",objectMBean.getObjectName());
        assertNull("This method call always returns null in the actual code",objectMBean.getObjectNameBasis());
        assertNull("This method call always returns null in the actual code",objectMBean.getObjectContextBasis());
        assertEquals("Mbean container should be equal",container,objectMBean.getMBeanContainer());
        assertEquals("Mbean description must be equal to : Test the mbean extended stuff","Test the mbean extended stuff",objectMBeanInfo.getDescription());
    }
