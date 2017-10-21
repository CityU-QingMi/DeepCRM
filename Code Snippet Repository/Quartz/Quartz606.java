    public String[] getPropertyGroups(String prefix) {
        Enumeration<?> keys = props.propertyNames();
        HashSet<String> groups = new HashSet<String>(10);

        if (!prefix.endsWith(".")) {
            prefix += ".";
        }

        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            if (key.startsWith(prefix)) {
                String groupName = key.substring(prefix.length(), key.indexOf(
                        '.', prefix.length()));
                groups.add(groupName);
            }
        }

        return (String[]) groups.toArray(new String[groups.size()]);
    }
