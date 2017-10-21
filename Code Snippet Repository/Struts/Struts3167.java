    protected Object[] getArgs(Appendable out, GxpContext gxpContext, Map overrides) {
        List<Object> argList = getArgListFromValueStack(overrides);
        Object[] args = new Object[argList.size() + 2];
        args[0] = out;
        args[1] = gxpContext;
        int index = 2;
        for (Iterator<Object> i = argList.iterator(); i.hasNext(); index++) {
            args[index] = i.next();
        }
        return args;
    }
