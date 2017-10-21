    public List<String> regexFind(List<String> lines, String pattern)
    {
        List<String> hits = new ArrayList<>();

        Pattern patKey = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);

        Matcher mat;
        for (String line : lines)
        {
            mat = patKey.matcher(line);
            if (mat.matches())
            {
                if (mat.groupCount() >= 1)
                {
                    hits.add(mat.group(1));
                }
                else
                {
                    hits.add(mat.group(0));
                }
            }
        }

        return hits;
    }
