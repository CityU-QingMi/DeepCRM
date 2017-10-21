    public void testStrictDMIInheritanceDisabledInBothPackage() {
        // given
        PackageConfig parent = new PackageConfig.Builder("parent")
                .strictMethodInvocation(false)
                .build();

        // when
        PackageConfig child = new PackageConfig.Builder("child")
                .addParent(parent)
                .strictMethodInvocation(false)
                .build();

        // then
        assertFalse(child.isStrictMethodInvocation());
    }
