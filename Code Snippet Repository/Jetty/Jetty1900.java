    public static void dump(Appendable out, String indent) throws IOException
    {
        out.append("o.e.j.jndi.ContextFactory@").append(Long.toHexString(__contextMap.hashCode())).append("\n");
        int size=__contextMap.size();
        int i=0;
        for (Map.Entry<ClassLoader,NamingContext> entry : ((Map<ClassLoader,NamingContext>)__contextMap).entrySet())
        {
            boolean last=++i==size;
            ClassLoader loader=entry.getKey();
            out.append(indent).append(" +- ").append(loader.getClass().getSimpleName()).append("@").append(Long.toHexString(loader.hashCode())).append(": ");

            NamingContext context = entry.getValue();
            context.dump(out,indent+(last?"    ":" |  "));
        }
    }
