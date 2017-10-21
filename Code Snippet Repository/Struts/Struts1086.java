    public void testStrictDMIInheritance() {
        // given
        PackageConfig parent = new PackageConfig.Builder("parent").build();

        // when
        PackageConfig child = new PackageConfig.Builder("child")
                .addParent(parent)
                .build();

        // then
        assertTrue(child.isStrictMethodInvocation());
    }
