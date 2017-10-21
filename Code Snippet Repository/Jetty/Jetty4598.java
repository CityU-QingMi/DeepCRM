    private void parseAllArgs(List<String> lines, String origin)
    {
        for (String line : lines)
        {
            String arg = line;
            int idx = line.indexOf('=');
            if (idx > 0)
            {
                arg = line.substring(0,idx);
            }
            if (BANNED_ARGS.contains(arg))
            {
                throw new UsageException(ERR_BAD_ARG,"%s not allowed in %s",arg,origin);
            }
            this.props.addPossibleProperty(line,origin);
        }
    }
