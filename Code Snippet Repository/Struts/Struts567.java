    protected Collection<String> getStandardAttributes() {
        Class clz = getClass();
        Collection<String> standardAttributes = standardAttributesMap.get(clz);
        if (standardAttributes == null) {
            Collection<Method> methods = MethodUtils.getMethodsListWithAnnotation(clz, StrutsTagAttribute.class,
                    true, true);
            standardAttributes = new HashSet<>(methods.size());
            for(Method m : methods) {
                standardAttributes.add(StringUtils.uncapitalize(m.getName().substring(3)));
            }
            standardAttributesMap.putIfAbsent(clz, standardAttributes);
        }
        return standardAttributes;
    }
