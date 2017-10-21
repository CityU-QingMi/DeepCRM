    public synchronized void logContext(int atLevel, String prefix,
                                        String message, String suffix) {

        if (level < atLevel) {
            return;
        }

        if (writer == null) {
            return;
        }

        sb.append(HsqlDateTime.getSystemTimeString()).append(' ');

        if (!isSQL) {
            sb.append(logTypeNames[atLevel]).append(' ');
        }

        sb.append(prefix).append(' ');
        sb.append(message).append(' ').append(suffix);
        writer.println(sb.toString());
        sb.setLength(0);
        writer.flush();
    }
