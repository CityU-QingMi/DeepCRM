        private void assertNoMissingParameters(Field field, int arity, Stack<String> args) {
            if (arity > args.size()) {
                int actualSize = 0;
                @SuppressWarnings("unchecked")
                Stack<String> copy = (Stack<String>) args.clone();
                while (!copy.isEmpty()) {
                    actualSize += split(copy.pop(), field).length;
                    if (actualSize >= arity) { return; }
                }
                if (arity == 1) {
                    if (field.isAnnotationPresent(Option.class)) {
                        throw new MissingParameterException("Missing required parameter for " +
                                optionDescription("", field, 0));
                    }
                    Range indexRange = Range.parameterIndex(field);
                    Help.IParamLabelRenderer labelRenderer = Help.createMinimalParamLabelRenderer();
                    String sep = "";
                    String names = "";
                    int count = 0;
                    for (int i = indexRange.min; i < positionalParametersFields.size(); i++) {
                        if (Range.parameterArity(positionalParametersFields.get(i)).min > 0) {
                            names += sep + labelRenderer.renderParameterLabel(positionalParametersFields.get(i),
                                    Help.Ansi.OFF, Collections.<IStyle>emptyList());
                            sep = ", ";
                            count++;
                        }
                    }
                    String msg = "Missing required parameter";
                    Range paramArity = Range.parameterArity(field);
                    if (paramArity.isVariable) {
                        msg += "s at positions " + indexRange + ": ";
                    } else {
                        msg += (count > 1 ? "s: " : ": ");
                    }
                    throw new MissingParameterException(msg + names);
                }
                throw new MissingParameterException(optionDescription("", field, 0) +
                        " requires at least " + arity + " values, but only " + args.size() + " were specified.");
            }
        }
