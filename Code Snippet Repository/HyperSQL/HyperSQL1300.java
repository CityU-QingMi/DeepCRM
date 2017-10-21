    public synchronized void logContext(int atLevel, String message) {

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

        sb.append(message);
        writer.println(sb.toString());
        sb.setLength(0);
        writer.flush();
    }
