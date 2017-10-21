    private static JavaVersion parseJDK9(String version)
    {
        Matcher matcher = JDK9.matcher(version);
        if (!matcher.matches())
            throw new IllegalArgumentException("Invalid Java version " + version);
        int major = Integer.parseInt(matcher.group(1));
        String minorGroup = matcher.group(3);
        int minor = minorGroup == null || minorGroup.isEmpty() ? 0 : Integer.parseInt(minorGroup);
        String microGroup = matcher.group(5);
        int micro = microGroup == null || microGroup.isEmpty() ? 0 : Integer.parseInt(microGroup);
        String suffix = matcher.group(6);
        return new JavaVersion(version, major, major, minor, micro, 0, suffix);
    }
