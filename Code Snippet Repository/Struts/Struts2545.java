    private String extractClassName(String source) {
        Matcher matcher = PACKAGE_PATTERN.matcher(source);
        matcher.find();
        String packageName = matcher.group(1);

        matcher = CLASS_PATTERN.matcher(source);
        matcher.find();
        String className = matcher.group(1);

        return packageName + "." + className;
    }
