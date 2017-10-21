    public synchronized void logContext(Throwable t, String message,
                                        int atLevel) {

        if (level == LOG_NONE) {
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

        Throwable           temp     = new Throwable();
        StackTraceElement[] elements = temp.getStackTrace();

        if (elements.length > 1) {
            sb.append(' ');
            sb.append(elements[1].getClassName()).append('.');
            sb.append(elements[1].getMethodName());
        }

        elements = t.getStackTrace();

        if (elements.length > 0) {
            sb.append(' ');
            sb.append(elements[0].getClassName()).append('.');
            sb.append(' ').append(elements[0].getMethodName());
        }

        sb.append(' ').append(t.toString());
        writer.println(sb.toString());
        sb.setLength(0);
        writer.flush();
    }
