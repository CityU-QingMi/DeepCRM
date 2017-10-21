    public static boolean isProxyMember(Member member, Object object) {
        if (!isProxy(object))
            return false;

        Boolean flag = isProxyMemberCache.get(member);
        if (flag != null) {
            return flag;
        }

        boolean isProxyMember = isSpringProxyMember(member);

        isProxyMemberCache.put(member, isProxyMember);
        return isProxyMember;
    }
