    public void update(BaseHome baseHome,Props props) throws IOException
    {
        String update = getFile().getFileName().toString();
        update = update.substring(0,update.lastIndexOf("."));
        String source = baseHome.toShortForm(getFile());
        
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(getFile(),StandardCharsets.UTF_8,StandardOpenOption.TRUNCATE_EXISTING,StandardOpenOption.CREATE)))
        {
            for (String line : getAllLines())
            {
                Matcher m = Module.SET_PROPERTY.matcher(line);
                if (m.matches() && m.groupCount()==3)
                {
                    String name = m.group(2);
                    String value = m.group(3);
                    Prop p = props.getProp(name);
                    if (p!=null && ("#".equals(m.group(1)) || !value.equals(p.value)))
                    {
                        StartLog.info("%-15s property updated %s=%s",update,name,p.value);
                        writer.printf("%s=%s%n",name,p.value);
                    }
                    else
                    {
                        writer.println(line);
                    }
                }
                else
                {
                    writer.println(line);
                }
            }
        }

        StartLog.info("%-15s updated %s",update,source);
    }
