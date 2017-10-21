    public boolean end(Writer writer, String body) {
        actualName = findString(name, "name", "You must specify the i18n key. Example: welcome.header");
        String defaultMessage;
        if (StringUtils.isNotEmpty(body)) {
            defaultMessage = body;
        } else {
            defaultMessage = actualName;
        }

        Boolean doSearchStack = false;
        if (searchStack != null) {
            Object value = findValue(searchStack, Boolean.class);
            doSearchStack = value != null ? (Boolean) value : false;
        }

        String msg = TextProviderHelper.getText(actualName, defaultMessage, values, getStack(), doSearchStack);

        if (msg != null) {
            try {
                if (getVar() == null) {
                    writer.write(msg);
                } else {
                    putInContext(msg);
                }
            } catch (IOException e) {
                LOG.error("Could not write out Text tag", e);
            }
        }

        return super.end(writer, "");
    }
