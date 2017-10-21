    @Test
    public void testKeySet()
    {
        // given
        assertFalse("Managed is not registered with loggers",logMBean.getLoggers().contains(MANAGED_CLASS));

        // when
        logMBean.setDebugEnabled(MANAGED_CLASS,true);

        // then
        assertTrue("Managed must be registered with loggers",logMBean.getLoggers().contains(MANAGED_CLASS));
        assertTrue("This must return true as debug is enabled for this class",logMBean.isDebugEnabled(MANAGED_CLASS));
    }
