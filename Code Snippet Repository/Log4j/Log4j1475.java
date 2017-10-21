        public String abbreviatedSynopsis() {
            StringBuilder sb = new StringBuilder();
            if (!optionFields.isEmpty()) { // only show if annotated object actually has options
                sb.append(" [OPTIONS]");
            }
            // sb.append(" [--] "); // implied
            for (Field positionalParam : positionalParametersFields) {
                if (!positionalParam.getAnnotation(Parameters.class).hidden()) {
                    sb.append(' ').append(parameterLabelRenderer.renderParameterLabel(positionalParam, ansi(), colorScheme.parameterStyles));
                }
            }
            return colorScheme.commandText(commandName).toString()
                    + (sb.toString()) + System.getProperty("line.separator");
        }
