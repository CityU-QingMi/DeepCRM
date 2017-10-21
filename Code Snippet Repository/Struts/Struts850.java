    public static String getText(String key, String defaultMessage, List<Object> args, ValueStack stack, boolean searchStack) {
        String msg = null;
        TextProvider tp = null;

        for (Object o : stack.getRoot()) {
            if (o instanceof TextProvider) {
                tp = (TextProvider) o;
                msg = tp.getText(key, null, args, stack);

                break;
            }
        }

        if (msg == null) {
            // evaluate the defaultMessage as an OGNL expression
            if (searchStack)
                msg = stack.findString(defaultMessage);
            
            if (msg == null) {
                // use the defaultMessage literal value
                msg = defaultMessage;
                msg = StringEscapeUtils.escapeEcmaScript(msg);
                msg = StringEscapeUtils.escapeHtml4(msg);
                LOG.debug("Message for key '{}' is null, returns escaped default message [{}]", key, msg);
            }

            if (LOG.isWarnEnabled()) {
                if (tp != null) {
                    LOG.warn("The first TextProvider in the ValueStack ({}) could not locate the message resource with key '{}'", tp.getClass().getName(), key);
                } else {
                    LOG.warn("Could not locate the message resource '{}' as there is no TextProvider in the ValueStack.", key);
                }
                if (defaultMessage.equals(msg)) {
                    LOG.warn("The default value expression '{}' was evaluated and did not match a property. The literal value '{}' will be used.", defaultMessage, defaultMessage);
                } else {
                    LOG.warn("The default value expression '{}' evaluated to '{}'", defaultMessage, msg);
                }
            }
        }
        return msg;
    }
