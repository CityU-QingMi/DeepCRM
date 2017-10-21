    @Override
    public void printStack(final StringBuilder sb, final StackTraceElement[] stack) {
        int i = 0;
        for (final StackTraceElement element : stack) {
            sb.append("\tat ").append(element.toString());
            sb.append('\n');
            if (i == 0 && threadInfo.getLockInfo() != null) {
                final Thread.State ts = threadInfo.getThreadState();
                switch (ts) {
                    case BLOCKED:
                        sb.append("\t-  blocked on ");
                        formatLock(sb, threadInfo.getLockInfo());
                        sb.append('\n');
                        break;
                    case WAITING:
                        sb.append("\t-  waiting on ");
                        formatLock(sb, threadInfo.getLockInfo());
                        sb.append('\n');
                        break;
                    case TIMED_WAITING:
                        sb.append("\t-  waiting on ");
                        formatLock(sb, threadInfo.getLockInfo());
                        sb.append('\n');
                        break;
                    default:
                }
            }

            for (final MonitorInfo mi : threadInfo.getLockedMonitors()) {
                if (mi.getLockedStackDepth() == i) {
                    sb.append("\t-  locked ");
                    formatLock(sb, mi);
                    sb.append('\n');
                }
            }
            ++i;
        }

        final LockInfo[] locks = threadInfo.getLockedSynchronizers();
        if (locks.length > 0) {
            sb.append("\n\tNumber of locked synchronizers = ").append(locks.length).append('\n');
            for (final LockInfo li : locks) {
                sb.append("\t- ");
                formatLock(sb, li);
                sb.append('\n');
            }
        }
    }
