    @Override
    public void printThreadInfo(final StringBuilder sb) {
        StringBuilders.appendDqValue(sb, threadInfo.getThreadName());
        sb.append(" Id=").append(threadInfo.getThreadId()).append(' ');
        formatState(sb, threadInfo);
        if (threadInfo.isSuspended()) {
            sb.append(" (suspended)");
        }
        if (threadInfo.isInNative()) {
            sb.append(" (in native)");
        }
        sb.append('\n');
    }
