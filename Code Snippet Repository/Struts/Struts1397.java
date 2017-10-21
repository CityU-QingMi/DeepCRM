    public void testAccessEnum() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);

        // when
        Member values = MyValues.class.getMethod("values");
        boolean actual = sma.isAccessible(context, MyValues.class, values, null);

        // then
        assertTrue("Access to enums is blocked!", actual);
    }
