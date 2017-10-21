    private static boolean hasMember(Class<?> clazz, Member member) {
        if (member instanceof Method) {
            return null != MethodUtils.getMatchingMethod(clazz, member.getName(), ((Method) member).getParameterTypes());
        }
        if (member instanceof Field) {
            return null != FieldUtils.getField(clazz, member.getName(), true);
        }
        if (member instanceof Constructor) {
            return null != ConstructorUtils.getMatchingAccessibleConstructor(clazz, ((Constructor) member).getParameterTypes());
        }

        return false;
    }
