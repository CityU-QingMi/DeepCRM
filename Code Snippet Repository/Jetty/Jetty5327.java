    @Override
    public void dump(Appendable out, String indent) throws IOException
    {
        ContainerLifeCycle.dumpObject(out, this);
        Thread thread = this.thread;
        if (thread != null)
        {
            List<StackTraceElement> frames = Arrays.asList(thread.getStackTrace());
            ContainerLifeCycle.dump(out, indent, frames);
        }
    }
