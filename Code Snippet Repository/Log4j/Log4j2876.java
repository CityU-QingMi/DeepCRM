        public StringBuilder toSerializable(final LogEvent event, final StringBuilder buf) {
            //final StringBuilder buf = getStringBuilder();
            final int len = formatters.length;
            for (int i = 0; i < len; i++) {
                formatters[i].format(event, buf);
            }
            //            String str = buf.toString();
            //            if (replace != null) {
            //                str = replace.format(str);
            //            }
            return buf;
        }
