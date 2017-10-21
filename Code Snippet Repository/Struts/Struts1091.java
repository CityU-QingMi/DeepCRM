    public void testCheckSubstitutionsMatch() {
        ActionConfig m = matcher.match("foo/class/method");

        assertTrue("Class hasn't been replaced", "foo.bar.classAction".equals(m.getClassName()));
        assertTrue("Method hasn't been replaced", "domethod".equals(m.getMethodName()));
        assertTrue("Package isn't correct", "package-class".equals(m.getPackageName()));

        assertTrue("First param isn't correct", "class".equals(m.getParams().get("first")));
        assertTrue("Second param isn't correct", "method".equals(m.getParams().get("second")));
        
        ExceptionMappingConfig ex = m.getExceptionMappings().get(0);
        assertTrue("Wrong name, was "+ex.getName(), "fooclass".equals(ex.getName()));
        assertTrue("Wrong result", "successclass".equals(ex.getResult()));
        assertTrue("Wrong exception", 
                "java.lang.methodException".equals(ex.getExceptionClassName()));
        assertTrue("First param isn't correct", "class".equals(ex.getParams().get("first")));
        assertTrue("Second param isn't correct", "method".equals(ex.getParams().get("second")));
        
        ResultConfig result = m.getResults().get("successclass");
        assertTrue("Wrong name, was "+result.getName(), "successclass".equals(result.getName()));
        assertTrue("Wrong classname", "foo.method".equals(result.getClassName()));
        assertTrue("First param isn't correct", "class".equals(result.getParams().get("first")));
        assertTrue("Second param isn't correct", "method".equals(result.getParams().get("second")));
        
    }
