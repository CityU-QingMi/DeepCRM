    protected Map createDefaultContext(Object root, ClassResolver classResolver) {
        ClassResolver resolver = classResolver;
        if (resolver == null) {
            resolver = container.getInstance(CompoundRootAccessor.class);
        }

        SecurityMemberAccess memberAccess = new SecurityMemberAccess(allowStaticMethodAccess);
        memberAccess.setExcludedClasses(excludedClasses);
        memberAccess.setExcludedPackageNamePatterns(excludedPackageNamePatterns);
        memberAccess.setExcludedPackageNames(excludedPackageNames);
        memberAccess.setDisallowProxyMemberAccess(disallowProxyMemberAccess);

        return Ognl.createDefaultContext(root, resolver, defaultConverter, memberAccess);
    }
