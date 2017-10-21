    @Override
    public void dump(Appendable out, String indent) throws IOException
    {
        ContainerLifeCycle.dumpObject(out, this);
        List<String> children = new ArrayList<>();
        children.add(String.format("connections=%s", _connections));
        children.add(String.format("durations=%s", _connectionsDuration));
        children.add(String.format("bytes in/out=%s/%s", getReceivedBytes(), getSentBytes()));
        children.add(String.format("messages in/out=%s/%s", getReceivedMessages(), getSentMessages()));
        ContainerLifeCycle.dump(out, indent, children);
    }
