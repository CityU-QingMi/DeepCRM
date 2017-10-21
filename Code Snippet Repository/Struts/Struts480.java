    protected String getPrintable(String indent, long minTime) {
        //only print the value if we are larger or equal to the min time.
        if (totalTime >= minTime) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(indent);
            buffer.append("[" + totalTime + "ms] - " + resource);
            buffer.append("\n");

            for (ProfilingTimerBean aChildren : children) {
                buffer.append((aChildren).getPrintable(indent + "  ", minTime));
            }

            return buffer.toString();
        } else
            return "";
    }
