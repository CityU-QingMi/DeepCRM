    private static String[] genWinArgs(String monolithic) {
        List<String> list = new ArrayList<String>();
        list.add("cmd.exe");
        list.add("/y");
        list.add("/c");
        Matcher m = wincmdPattern.matcher(monolithic);
        while (m.find()) for (int i = 1; i <= m.groupCount(); i++) {
            if (m.group(i) == null) continue;
            if (m.group(i).length() > 1 && m.group(i).charAt(0) == '"') {
                list.add(m.group(i).substring(1, m.group(i).length() - 1));
                continue;
            }
            list.addAll(Arrays.asList(m.group(i).split("\\s+", -1)));
        }
        return list.toArray(new String[] {});
    }
