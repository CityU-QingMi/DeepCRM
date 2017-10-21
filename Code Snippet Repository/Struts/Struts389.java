    private static boolean isSpringProxyMember(Member member) {
        try {
            Class<?> clazz = ClassLoaderUtil.loadClass(SPRING_ADVISED_CLASS_NAME, ProxyUtil.class);
            if (hasMember(clazz, member))
                return true;
            clazz = ClassLoaderUtil.loadClass(SPRING_TARGETCLASSAWARE_CLASS_NAME, ProxyUtil.class);
            if (hasMember(clazz, member))
                return true;
            clazz = ClassLoaderUtil.loadClass(SPRING_SPRINGPROXY_CLASS_NAME, ProxyUtil.class);
            if (hasMember(clazz, member))
                return true;
        } catch (ClassNotFoundException ignored) {
        }

        return false;
    }
