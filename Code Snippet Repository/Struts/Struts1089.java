    public void testStrictDMIInheritanceDisabledInChildPackage() {
        // given
        PackageConfig parent = new PackageConfig.Builder("parent").build();

        // when
        PackageConfig child = new PackageConfig.Builder("child")
                .addParent(parent)
                .strictMethodInvocation(false)
                .build();

        // then
        assertFalse(child.isStrictMethodInvocation());
    }
