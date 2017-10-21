    private static JavaVersion parsePreJDK9(String version)
    {
        Matcher matcher = PRE_JDK9.matcher(version);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid Java version " + version);
        int major = 1;
        int minor = Integer.parseInt(matcher.group(1));
        String microGroup = matcher.group(3);
        int micro = microGroup == null || microGroup.isEmpty() ? 0 : Integer.parseInt(microGroup);
        String updateGroup = matcher.group(5);
        int update = updateGroup == null || updateGroup.isEmpty() ? 0 : Integer.parseInt(updateGroup);
        String suffix = matcher.group(6);
        return new JavaVersion(version, minor, major, minor, micro, update, suffix);
    }
