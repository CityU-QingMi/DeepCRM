    public void testCopyEditable() {
        Foo foo1 = new Foo();
        Foo foo2 = new Foo();

        Map<String, Object> context = ognlUtil.createDefaultContext(foo1);

        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 29);
        cal.set(Calendar.YEAR, 2017);

        foo1.setTitle("blah");
        foo1.setNumber(1);
        foo1.setPoints(new long[]{1, 2, 3});
        foo1.setBirthday(cal.getTime());
        foo1.setUseful(false);

        ognlUtil.copy(foo1, foo2, context, null, null, Bar.class);

        assertEquals(foo1.getTitle(), foo2.getTitle());
        assertEquals(0, foo2.getNumber());
        assertNull(foo2.getPoints());
        assertNull(foo2.getBirthday());
    }
