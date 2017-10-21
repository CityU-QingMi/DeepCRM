    protected Message exitMsg(final String format, final Object result) {
        if (result == null) {
            if (format == null) {
                return messageFactory.newMessage("Exit");
            }
            return messageFactory.newMessage("Exit: " + format);
        }
        if (format == null) {
            return messageFactory.newMessage("Exit with(" + result + ')');
        }
        return messageFactory.newMessage("Exit: " + format, result);

    }
