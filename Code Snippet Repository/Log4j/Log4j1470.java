        private String optionDescription(String prefix, Field field, int index) {
            Help.IParamLabelRenderer labelRenderer = Help.createMinimalParamLabelRenderer();
            String desc = "";
            if (field.isAnnotationPresent(Option.class)) {
                desc = prefix + "option '" + field.getAnnotation(Option.class).names()[0] + "'";
                if (index >= 0) {
                    Range arity = Range.optionArity(field);
                    if (arity.max > 1) {
                        desc += " at index " + index;
                    }
                    desc += " (" + labelRenderer.renderParameterLabel(field, Help.Ansi.OFF, Collections.<IStyle>emptyList()) + ")";
                }
            } else if (field.isAnnotationPresent(Parameters.class)) {
                Range indexRange = Range.parameterIndex(field);
                Text label = labelRenderer.renderParameterLabel(field, Help.Ansi.OFF, Collections.<IStyle>emptyList());
                desc = prefix + "positional parameter at index " + indexRange + " (" + label + ")";
            }
            return desc;
        }
