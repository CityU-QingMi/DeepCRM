    private static String shorten(BaseHome baseHome, Path path, Path testResourcesDir)
    {
        String value = baseHome.toShortForm(path);
        if (value.startsWith("${"))
        {
            return value;
        }
        
        if (path.startsWith(testResourcesDir))
        {
            int len = testResourcesDir.toString().length();
            value = "${maven-test-resources}" + value.substring(len);
        }
        return value;
    }
