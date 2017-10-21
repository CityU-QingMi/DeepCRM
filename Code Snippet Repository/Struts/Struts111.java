    public static List<String> buildParentListFromString(String parent) {
        if (StringUtils.isEmpty(parent)) {
            return Collections.emptyList();
        }

        StringTokenizer tokenizer = new StringTokenizer(parent, ",");
        List<String> parents = new ArrayList<>();

        while (tokenizer.hasMoreTokens()) {
            String parentName = tokenizer.nextToken().trim();

            if (StringUtils.isNotEmpty(parentName)) {
                parents.add(parentName);
            }
        }

        return parents;
    }
