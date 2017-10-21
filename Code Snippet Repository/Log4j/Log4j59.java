    @Test
    public void testCalcLocation() {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
        final StackTraceElement element = new Foo().foo();
        assertEquals("org.apache.logging.log4j.util.StackLocatorTest$Foo", element.getClassName());
        assertEquals(100, element.getLineNumber());
    }
