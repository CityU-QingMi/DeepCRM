    @Override
    public void printThreadInfo(final StringBuilder sb) {
        StringBuilders.appendDqValue(sb, name).append(Chars.SPACE);
        if (isDaemon) {
            sb.append("daemon ");
        }
        sb.append("prio=").append(priority).append(" tid=").append(id).append(' ');
        if (threadGroupName != null) {
            StringBuilders.appendKeyDqValue(sb, "group", threadGroupName);
        }
        sb.append('\n');
        sb.append("\tThread state: ").append(state.name()).append('\n');
    }
