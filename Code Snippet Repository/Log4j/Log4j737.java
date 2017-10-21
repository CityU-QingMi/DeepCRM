    private Throwable convertString(final ListIterator<String> lines, final boolean removeCausedBy) {
        String firstLine = lines.next();
        if (removeCausedBy) {
            firstLine = firstLine.substring(CAUSED_BY_STRING_LENGTH);
        }
        final int colon = firstLine.indexOf(":");
        String throwableClassName;
        String message = null;
        if (colon > 1) {
            throwableClassName = firstLine.substring(0, colon);
            if (firstLine.length() > colon + 1) {
                message = firstLine.substring(colon + 1).trim();
            }
        } else {
            throwableClassName = firstLine;
        }

        final List<StackTraceElement> stackTrace = new ArrayList<>();
        Throwable cause = null;
        while (lines.hasNext()) {
            final String line = lines.next();

            if (line.startsWith("Caused by ")) {
                lines.previous();
                cause = convertString(lines, true);
                break;
            }

            stackTrace.add(
                    StackTraceElementAttributeConverter.convertString(line.trim().substring(3).trim())
            );
        }

        return this.getThrowable(throwableClassName, message, cause,
                stackTrace.toArray(new StackTraceElement[stackTrace.size()]));
    }
