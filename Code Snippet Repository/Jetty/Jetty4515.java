    public void writeIniSection(BufferedWriter writer, Props props)
    {
        PrintWriter out = new PrintWriter(writer);
        out.println("# --------------------------------------- ");
        out.println("# Module: " + getName());
        for (String line : getDescription())
            out.append("# ").println(line);
        out.println("# --------------------------------------- ");
        out.println("--module=" + getName());
        out.println();
        for (String line : getIniTemplate())
        {
            Matcher m = SET_PROPERTY.matcher(line);
            if (m.matches() && m.groupCount()==3)
            {
                String name = m.group(2);
                Prop p = props.getProp(name);
                if (p!=null && p.origin.startsWith(CommandLineConfigSource.ORIGIN_CMD_LINE))
                {
                    StartLog.info("%-15s property set %s=%s",this._name,name,p.value);
                    out.printf("%s=%s%n",name,p.value);
                }
                else
                    out.println(line);
            }
            else
                out.println(line);
        }
        out.println();
        out.flush();
    }
