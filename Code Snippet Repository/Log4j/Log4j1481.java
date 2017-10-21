            public Text[][] render(Parameters params, Field field, IParamLabelRenderer paramLabelRenderer, ColorScheme scheme) {
                Text label = paramLabelRenderer.renderParameterLabel(field, scheme.ansi(), scheme.parameterStyles);
                Text requiredParameter = scheme.parameterText(Range.parameterArity(field).min > 0 ? requiredMarker : "");

                final int COLUMN_COUNT = 5;
                final Text EMPTY = Ansi.EMPTY_TEXT;
                Text[][] result = new Text[Math.max(1, params.description().length)][COLUMN_COUNT];
                result[0] = new Text[] { requiredParameter, EMPTY, EMPTY, label, scheme.ansi().new Text(str(params.description(), 0)) };
                for (int i = 1; i < params.description().length; i++) {
                    result[i] = new Text[] { EMPTY, EMPTY, EMPTY, EMPTY, scheme.ansi().new Text(params.description()[i]) };
                }
                return result;
            }
