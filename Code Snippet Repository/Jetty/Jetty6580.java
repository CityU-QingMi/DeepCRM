    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("ExtensionStack[");
        s.append("queueSize=").append(getQueueSize());
        s.append(",extensions=");
        if (extensions == null)
        {
            s.append("<null>");
        }
        else
        {
            s.append('[');
            boolean delim = false;
            for (Extension ext : extensions)
            {
                if (delim)
                {
                    s.append(',');
                }
                if (ext == null)
                {
                    s.append("<null>");
                }
                else
                {
                    s.append(ext.getName());
                }
                delim = true;
            }
            s.append(']');
        }
        s.append(",incoming=").append((this.nextIncoming == null)?"<null>":this.nextIncoming.getClass().getName());
        s.append(",outgoing=").append((this.nextOutgoing == null)?"<null>":this.nextOutgoing.getClass().getName());
        s.append("]");
        return s.toString();
    }
