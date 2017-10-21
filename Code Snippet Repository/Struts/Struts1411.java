    public void testMiddleOfInheritanceExclusion3() throws Exception {
        // given
        SecurityMemberAccess sma = new SecurityMemberAccess(false);

        String propertyName = "barLogic";
        Member member = BarInterface.class.getMethod(propertyName);

        // when
        boolean accessible = sma.isAccessible(context, target, member, propertyName);

        // then
        assertTrue("barLogic() from BarInterface isn't accessible!!!", accessible);
    }
