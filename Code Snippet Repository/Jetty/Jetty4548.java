    public void addArg(final String rawline, final String source)
    {
        if (rawline == null)
        {
            return;
        }

        String line = rawline.trim();
        if (line.length() == 0)
        {
            return;
        }

        args.add(new Entry(line,source));
    }
