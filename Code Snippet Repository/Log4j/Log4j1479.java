            public Text[][] render(Option option, Field field, IParamLabelRenderer paramLabelRenderer, ColorScheme scheme) {
                String[] names = ShortestFirst.sort(option.names());
                int shortOptionCount = names[0].length() == 2 ? 1 : 0;
                String shortOption = shortOptionCount > 0 ? names[0] : "";
                Text paramLabelText = paramLabelRenderer.renderParameterLabel(field, scheme.ansi(), scheme.optionParamStyles);
                String longOption = join(names, shortOptionCount, names.length - shortOptionCount, ", ");
                String sep = shortOptionCount > 0 && names.length > 1 ? "," : "";

                // if no long option, fill in the space between the short option name and the param label value
                if (paramLabelText.length > 0 && longOption.length() == 0) {
                    sep = paramLabelRenderer.separator();
                    paramLabelText = paramLabelText.substring(sep.length());
                }
                Text longOptionText = scheme.optionText(longOption);
                longOptionText = longOptionText.append(paramLabelText);
                String requiredOption = option.required() ? requiredMarker : "";

                boolean showDefault = command != null && !option.help() && !isBoolean(field.getType());
                Object defaultValue = null;
                try {
                    defaultValue = field.get(command);
                    if (defaultValue != null && field.getType().isArray()) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < Array.getLength(defaultValue); i++) {
                            sb.append(i > 0 ? ", " : "").append(Array.get(defaultValue, i));
                        }
                        defaultValue = sb.insert(0, "[").append("]").toString();
                    }
                } catch (Exception ex) {
                    showDefault = false;
                }
                final int descriptionCount = Math.max(1, option.description().length);
                final int ROW_COUNT = showDefault ? descriptionCount + 1 : descriptionCount;
                final int COLUMN_COUNT = 5;
                Text EMPTY = Ansi.EMPTY_TEXT;
                Text[][] result = new Text[ROW_COUNT][COLUMN_COUNT];
                result[0] = new Text[] { scheme.optionText(requiredOption), scheme.optionText(shortOption),
                        scheme.ansi().new Text(sep), longOptionText, scheme.ansi().new Text(str(option.description(), 0)) };
                for (int i = 1; i < option.description().length; i++) {
                    result[i] = new Text[] { EMPTY, EMPTY, EMPTY, EMPTY, scheme.ansi().new Text(option.description()[i]) };
                }
                if (showDefault) {
                    Arrays.fill(result[result.length - 1], EMPTY);
                    int row = empty(result[ROW_COUNT - 2][COLUMN_COUNT - 1]) ? ROW_COUNT - 2 : ROW_COUNT - 1;
                    result[row][COLUMN_COUNT - 1] = scheme.ansi().new Text("  Default: " + defaultValue);
                }
                return result;
            }
