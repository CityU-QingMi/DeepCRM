    protected WaitSection(HsqlArrayList linesArray) {

/**/
/**/
        lines = (String[]) linesArray.toArray();

        int    closeCmd = lines[0].indexOf("*/");
        String cmd      = lines[0].substring(0, closeCmd);

        lines[0] = lines[0].substring(closeCmd + 2).trim();

        String trimmed = cmd.trim();

        if (trimmed.indexOf('e') < 0 && trimmed.indexOf('E') < 0) {

            // Does not contain "true" or "false"
            sleepTime = Long.parseLong(trimmed);
        } else {
            try {

                // Would like to use String.split(), but don't know if Java 4
                // is allowed here.
                // Until we can use Java 4, prohibit tabs as white space.
                int index = trimmed.indexOf(' ');

                if (index < 0) {
                    throw new IllegalArgumentException();
                }

                enforceSequence = Boolean.valueOf(trimmed.substring(0,
                        index)).booleanValue();
                waiter = Waiter.getWaiter(trimmed.substring(index).trim());
            } catch (IllegalArgumentException ie) {
                throw new IllegalArgumentException(W_SYNTAX_MSG);
            }
        }

        type = 'w';
    }
