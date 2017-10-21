    public void addSetting(String name, String value) throws TemplateException {
        // Process all other init-params:
        if (name.equals(INITPARAM_NOCACHE)) {
            nocache = StringUtil.getYesNo(value);
        } else if (name.equals(INITPARAM_DEBUG)) {
            debug = StringUtil.getYesNo(value);
        } else if (name.equals(INITPARAM_CONTENT_TYPE)) {
            contentType = value;
        } else {
            config.setSetting(name, value);
        }

        if (contentType != null && !contentTypeEvaluated) {
            int i = contentType.toLowerCase().indexOf("charset=");
            contentTypeEvaluated = true;
            if (i != -1) {
                char c = ' ';
                i--;
                while (i >= 0) {
                    c = contentType.charAt(i);
                    if (!Character.isWhitespace(c)) break;
                    i--;
                }
                if (i == -1 || c == ';') {
                    noCharsetInContentType = false;
                }
            }
        }
    }
