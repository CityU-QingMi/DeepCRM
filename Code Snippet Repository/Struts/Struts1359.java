    public void testMismatchedGettersAndSettersCauseExceptionInSet() {
        OgnlValueStack vs = createValueStack();

        BadJavaBean bean = new BadJavaBean();
        vs.push(bean);

        //this used to fail in OGNl versdion < 2.7
        vs.setValue("count", "1", true);
        assertEquals("1", bean.getCount());

        try {
            vs.setValue("count2", "a", true);
            fail("Expected an exception for mismatched getter and setter");
        } catch (XWorkException e) {
            //expected
        }
    }
