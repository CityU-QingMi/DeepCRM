    public String asProperty() {
        StringWriter sw = new StringWriter();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(sw);
            writer.print(rule);
            writer.print(property);
            writer.print("=");
            if ( rule.startsWith(DefaultObjectTypeDeterminer.KEY_PROPERTY_PREFIX) && value != null && value.length() > 0 ) {
                writer.print(value);
            } else {
                writer.print(typeConverter);
            }
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }

        return sw.toString();

    }
