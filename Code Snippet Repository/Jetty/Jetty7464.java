        public void parse(String line)
        {
            for (ConsolePattern pat : patterns)
            {
                Matcher mat = pat.getMatcher(line);
                if (mat.find())
                {
                    int num = 0, count = mat.groupCount();
                    String[] match = new String[count];
                    while (num++ < count)
                    {
                        match[num - 1] = mat.group(num);
                    }
                    pat.getMatches().add(match);

                    if (pat.getCount() > 0)
                    {
                        getLatch().countDown();
                    }
                }
            }
        }
