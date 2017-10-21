    public void dump(Appendable out,String indent) throws IOException
    {
        out.append(this.getClass().getSimpleName()).append("@").append(Long.toHexString(this.hashCode())).append("\n");
        int size=_bindings.size();
        int i=0;
        for (Map.Entry<String,Binding> entry : ((Map<String,Binding>)_bindings).entrySet())
        {
            boolean last=++i==size;
            out.append(indent).append(" +- ").append(entry.getKey()).append(": ");

            Binding binding = entry.getValue();
            Object value = binding.getObject();

            if ("comp".equals(entry.getKey()) && value instanceof Reference && "org.eclipse.jetty.jndi.ContextFactory".equals(((Reference)value).getFactoryClassName()))
            {
                ContextFactory.dump(out,indent+(last?"    ":" |  "));
            }
            else if (value instanceof Dumpable)
            {
                ((Dumpable)value).dump(out,indent+(last?"    ":" |  "));
            }
            else
            {
                out.append(value.getClass().getSimpleName()).append("=");
                out.append(String.valueOf(value).replace('\n','|').replace('\r','|'));
                out.append("\n");
            }
        }
    }
