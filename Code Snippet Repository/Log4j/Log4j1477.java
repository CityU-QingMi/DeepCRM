        public String commandList() {
            if (commands.isEmpty()) { return ""; }
            int commandLength = maxLength(commands.keySet());
            Help.TextTable textTable = new Help.TextTable(ansi(),
                    new Help.Column(commandLength + 2, 2, Help.Column.Overflow.SPAN),
                    new Help.Column(usageHelpWidth - (commandLength + 2), 2, Help.Column.Overflow.WRAP));

            for (Map.Entry<String, Help> entry : commands.entrySet()) {
                Help command = entry.getValue();
                String header = command.header != null && command.header.length > 0 ? command.header[0]
                        : (command.description != null && command.description.length > 0 ? command.description[0] : "");
                textTable.addRowValues(colorScheme.commandText(entry.getKey()), ansi().new Text(header));
            }
            return textTable.toString();
        }
