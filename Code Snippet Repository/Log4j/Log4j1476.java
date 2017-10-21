        public static StringBuilder join(Ansi ansi, String[] values, StringBuilder sb, Object... params) {
            if (values != null) {
                TextTable table = new TextTable(ansi, usageHelpWidth);
                table.indentWrappedLines = 0;
                for (String summaryLine : values) {
                    table.addRowValues(ansi.new Text(String.format(summaryLine, params)));
                }
                table.toString(sb);
            }
            return sb;
        }
