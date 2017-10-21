    public static String findAGroup(final String user) throws IOException {
        if (SystemUtils.IS_OS_MAC_OSX) {
            return "staff";
        }
        String group = user;
        try (FileInputStream fis = new FileInputStream("/etc/group")) {
            final List<String> groups = org.apache.commons.io.IOUtils.readLines(fis, Charset.defaultCharset());
            for (int i = 0; i < groups.size(); i++) {
                final String aGroup = groups.get(i);
                if (!aGroup.startsWith(user) && aGroup.contains(user)) {
                    group = aGroup.split(":")[0];
                    break;
                }
            }
        }
        return group;
    }
