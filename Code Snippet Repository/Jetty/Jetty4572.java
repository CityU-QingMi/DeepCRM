    @Override
    public void addUniqueLine(String line)
    {
        if (line.startsWith("--module="))
        {
            int idx = line.indexOf('=');
            String value = line.substring(idx + 1);
            for (String part : value.split(","))
            {
                super.addUniqueLine("--module=" + expandBaseDir(part));
            }
        }
        else
        {
            super.addUniqueLine(expandBaseDir(line));
        }
    }
