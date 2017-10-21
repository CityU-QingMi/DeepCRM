    public void testStrictDMIInheritanceDisabledInParentPackage() {
        // given
        PackageConfig parent = new PackageConfig.Builder("parent")
                .strictMethodInvocation(false)
                .build();

        // when
        PackageConfig child = new PackageConfig.Builder("child")
                .addParent(parent)
                .build();

        // then
        assertTrue(child.isStrictMethodInvocation());
    }
