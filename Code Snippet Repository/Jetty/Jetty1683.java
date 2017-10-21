    @Override
    public void dump(Appendable out, String indent) throws IOException
    {
        Selector selector = _selector;
        if (selector == null || !selector.isOpen())
            dumpBeans(out, indent);
        else
        {
            final ArrayList<Object> dump = new ArrayList<>(selector.keys().size() * 2);
            DumpKeys dumpKeys = new DumpKeys(dump);
            submit(dumpKeys);
            dumpKeys.await(5, TimeUnit.SECONDS);
            if (dump.isEmpty())
                dumpBeans(out, indent);
            else
                dumpBeans(out, indent, dump);
        }
    }
