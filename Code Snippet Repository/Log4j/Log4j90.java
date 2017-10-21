    private StringBuilder format(final MapFormat format, final StringBuilder sb) {
        if (format == null) {
            appendMap(sb);
        } else {
            switch (format) {
                case XML : {
                    asXml(sb);
                    break;
                }
                case JSON : {
                    asJson(sb);
                    break;
                }
                case JAVA : {
                    asJava(sb);
                    break;
                }
                default : {
                    appendMap(sb);
                }
            }
        }
        return sb;
    }
